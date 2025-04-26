package simplexity.simplecustomtab.objects;

import org.bukkit.permissions.Permission;

public record TabFormat(Permission permission, String format, int priority) {
}
