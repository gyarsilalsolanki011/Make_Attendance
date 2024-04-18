package com.gyarsilalsolanki011.makeattendance.repository.auth;

interface AuthRepository {
    Object getCurrentUser();
    Object register(String email, String password);
    Object login(String email, String password);
    void logout();
}
