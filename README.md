# Simple Custom Tab
Customize the tablist with PAPI and minimessage

## Commands and permissions:
- `/sctreload`
  - Reloads the configuration
  - `sct.reload` permission

Custom permissions can be used for sorting players and using custom formats, this is available in the config:

## Config

```yml
# included placeholders are <displayname> and <username> for the user's display name and username
header: "<red>Header"
footer: "<gradient:red:blue>-=-=-=-=-=-=-=-=-=-</gradient>"
player: "<papi:luckperms_prefix> <papi:player_displayname>"
update-ticks: 20
sort-users:
  enabled: false
  # This list can be expanded, the permissions are customizable.
  # The highest numbers will be on the top, lowest will be bottom.
  sort-order:
    5: "tablist.order.admin"
    4: "tablist.order.mod"
    3: "tablist.order.donators"
    2: "tablist.order.voters"
    1: "tablist.order.members"
# Note, this will override the normal display.
# The topmost listed will be the highest priority.
# Permissions are customizable.
permission-formatting:
  enabled: false
  formats:
    admin:
      permission: "tablist.formatting.admin"
      format: "<bold>[</bold>ADMIN<bold>]</bold> <displayname>"
      priority: 5
    mod:
      permission: "tablist.formatting.mod"
      format: "<bold>[</bold>MOD<bold>]</bold> <displayname>"
      priority: 4
animated:
  enabled: false
  delay-ticks: 5
  header:
    - "EEEEeEEE"
    - "EEEeEEEE"
    - "EEeEEEEE"
    - "EeEEEEEE"
    - "eEEEEEEE"
    - "EEEEEEEe"
    - "EEEEEEeE"
    - "EEEEEeEE"
  footer:
    - "AAAAAAa"
    - "AAAAAaA"
    - "AAAAaAA"
    - "AAAaAAA"
    - "AAaAAAA"
    - "AaAAAAA"
    - "aAAAAAA"
```