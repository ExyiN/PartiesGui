# PartiesGUI
A Minecraft plugin that adds a customisable GUI to [Parties](https://www.spigotmc.org/resources/parties-an-advanced-parties-manager.3709/).  
This plugin was developed for my Minecraft server Amphiris.

## What's in the GUI ?
You can see the current party infos, your rank, the members and also modify some party's settings.  

The access to the settings menu is rank restricted. Available settings:
- name (chat input)
- tag (chat input)
- description (chat input)
- MOTD (chat input)
- color

You can also teleport to the party home, but it only works if you have 1 max home (might implement multiple homes support if I need it).

Items are customisable. You can change their material, name, lore, etc.
## Important configuration
```yml
parties-commands: # Enter the main plugin commands if you changed it
  party: "party"
  sub-commands:
    color: "color"
    home: "home"
    motd: "motd"
    rename: "rename"
    tag: "tag"
  misc-commands:
    remove: "remove"
commands:
  partygui:
    command: "partygui"
    aliases: []
  partyguia:
    command: "partyguia"
    aliases: []
```
`parties-commands` are the main [Parties](https://www.spigotmc.org/resources/parties-an-advanced-parties-manager.3709/) plugin commands. Change them if you did in the main plugin to make settings modifications work.

`commands` are this plugin commands. You can change them to match your main plugin commands. For example, if you change parties to clans, you can put `clangui` as the main command.