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
            return pluginPermissions();
        }else{
            return applicationPermissions();
        }
    }

    private boolean isPlugin(ProtectionDomain domain) {
        return domain.getClassLoader() instanceof URLClassLoader;
    }

    private PermissionCollection pluginPermissions() {
        Permissions permissions = new Permissions(); // No permissions
        return permissions;
    }

    private PermissionCollection applicationPermissions() {
        Permissions permissions = new Permissions();
        permissions.add(new AllPermission());
        return permissions;
    }
}
