package com.GuTester.enums;

public enum Role {
    TESTER,
    DEVELOPER,
    ADMIN

//    CUSTOMER(Set.of(Permission.CUSTOMER_PERMISSION)),
//    EMPLOYEE(Set.of(Permission.CUSTOMER_PERMISSION, Permission.EMPLOYEE_PERMISSION)),
//    ADMIN(Set.of(Permission.CUSTOMER_PERMISSION, Permission.EMPLOYEE_PERMISSION, Permission.ADMIN_PERMISSION));
//
//    private final Set<Permission> permissions;
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.name()))
//                .collect(Collectors.toSet());
//    }
}
