package com.cos.security1.config.auth;


//시큐리티가 /login 을 낚아채서 로그인을 진행시킬 때
//완료가 되면 시큐리티가 관리하는 세션을 만들 것이다. (Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication => 유저 정보를 가진다.
// User 오브젝트 => UserDetails 타입 객체

//Security 세션 => Authentication => UserDetails (PrincipalDetails)

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user; //콤포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(()->user.getRole());
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //1년동안 회원이 로그인을 안하면 휴먼계정으로 할 것이다 -> User 모델에 loginDate 등의 필드를 가져야 한다.
        return true;
    }
}
