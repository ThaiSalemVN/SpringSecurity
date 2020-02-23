package thai.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import thai.security.service.UserService;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	/*
	 * Trong Spring Security, việc mã hóa mật khẩu sẽ do interface PasswordEncoder
	 * đảm nhận. PasswordEncoder có implementation là BCryptPasswordEncoder sẽ giúp
	 * chúng ta mã hóa mật khẩu bằng thuật toán BCrypt. Nhưng để sử dụng được
	 * PasswordEncoder, ta phải cấu hình để PasswordEncoder trở thành một Bean.
	 */
	
    @Bean///mã hóa mật khẩu  Bcrypt
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
            .passwordEncoder(passwordEncoder()); // Cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Cấu hình remember me, thời gian là 86400 giây = 1 ngày
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);
        
        // Không cho browser tự động cache
    	http
			.headers()
			.cacheControl();
    	
    	http
			.csrf()
				.disable()
        	.authorizeRequests()
        		.antMatchers("/trang-chu/**").permitAll() // Ko check đường dẫn này.      		
        		.antMatchers("/admin/**").hasAnyRole("ADMIN")
        		.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
        		.anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
        		.and()
        		.formLogin() // Cho phép người dùng xác thực bằng form login
	        		.loginPage("/trang-dang-nhap").permitAll() // Tất cả đều được truy cập vào địa chỉ này
	        		.defaultSuccessUrl("/trang-dang-nhap")//Controler đăng nhập  (default la "/")
	        		//.failureUrl("/login.html?error=true") 
	        		.loginProcessingUrl("/dang-nhap")//action        		
	        		.usernameParameter("username")
	                .passwordParameter("password")
	        		.and()
        		.logout() // Cho phép logout
        			.invalidateHttpSession(true) // Hủy session của người dùng
        			.clearAuthentication(true)
        			.logoutUrl("/dang-xuat")
	                .logoutSuccessUrl("/trang-dang-nhap");
    }
    
}
