package com.dsdsoft.sgp.authentication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dsdsoft.sgp.modelo.Usuario;
import com.dsdsoft.sgp.presentation.businessDelegate.IBusinessDelegatorView;


 
@Scope("singleton")
@Component("VortexAuthenticationProvider")
public class VortexAuthenticationProvider implements AuthenticationProvider {
     
    /**
     * Implementacion de la seguridad propia
     */
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	private Logger logger = LoggerFactory.getLogger(VortexAuthenticationProvider.class);
	
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
    	
    	try {
    		
	        String name = authentication.getName();
	        String password = authentication.getCredentials().toString();
	        
	        if(name == null || name.trim().equals("")){
	        	return null;
	        }
	        
			Object[] variables = { "emailUsuario", true, name.trim(), "="};
			List<Usuario> userSesion = businessDelegatorView.findByCriteriaInUsuario(variables, null, null);
			if (userSesion.isEmpty()) {
				return null;
			} else {
				Usuario login = userSesion.get(0);
				if (name != null && name.equals(login.getEmailUsuario()) && password != null && password.equals(login.getPasswordUsuario())) {
					final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
					grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
					grantedAuths.add(new SimpleGrantedAuthority("Admin"));
					final UserDetails principal = new User(name, password, grantedAuths);
					final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password,
							grantedAuths);

					return auth;

				} else {
					return null;
				}

			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
    	return null;
    }
    
    /**
     * Este metodo se llama primero cuando se autentica un usuario
     */
    @Override
    public boolean supports(Class<?> authentication) {
         return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
 
}