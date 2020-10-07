/*
 * Copyright (c) 2020.
 * Copyright by Tim and Felix
 */

package de.ft.interitus.plugin;

import java.net.URLClassLoader;
import java.security.*;

public class PluginSandboxSecurityPolicy extends Policy {


    @Override
    public PermissionCollection getPermissions(ProtectionDomain domain) {
        if(isPlugin(domain)) {
            return applicationPermissions();
        }else{
            return applicationPermissions();
        }
    }

    private boolean isPlugin(ProtectionDomain domain) {
        return domain.getClassLoader() instanceof URLClassLoader;
    }

    private PermissionCollection pluginPermissions() {
        return new Permissions();
    }

    private PermissionCollection applicationPermissions() {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return permissions;
    }
}
