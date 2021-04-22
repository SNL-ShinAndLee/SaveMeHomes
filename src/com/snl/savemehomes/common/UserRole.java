package com.snl.savemehomes.common;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
	NONE(0),
	ADMINISTRATOR(1);
	
	private int value;
    private static Map<Integer, UserRole> map = new HashMap<>();

    private UserRole(int value) {
        this.value = value;
    }

    static {
        for (UserRole userRole : UserRole.values()) {
            map.put(userRole.value, userRole);
        }
    }

    public static UserRole valueOf(int userRole) {
        return (UserRole) map.get(userRole);
    }

    public int getValue() {
        return value;
    }
}