package com.example.church_management_system.service;

import com.example.church_management_system.model.MemberRegistration;

import java.util.Optional;

public interface UserService {
    Optional<MemberRegistration> findUserByEmail(String email);

    MemberRegistration saveUser(MemberRegistration user);

    boolean emailExists(String email);
}
