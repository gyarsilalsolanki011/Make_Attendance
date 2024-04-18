package com.gyarsilalsolanki011.makeattendance.repository.auth;

interface AuthRepository {
    public Object getCurrentUser();
    public Object register(String email, String password);
    public Object login(String email, String password);
    public void logout();
}
