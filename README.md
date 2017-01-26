# SimpleThirst

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

[SimpleThirst Bukkit Plugin Page] (https://dev.bukkit.org/projects/simplethirst)

SimpleThirst allows for tons of customization for implementing thirst into your server. It was made for [this plugin request] (http://forums.bukkit.org/threads/supersimple-thirst.285571/). As stated by xXSilverswordXx "Starting at 30, the meter will slowly decrease (about 1 lost every 30 seconds). If the players meter is at 10, it will say: You start to feel thirsty.

If the meter is at 5, say: I need to find water! (at this point, the player will get random bouts of nausea, every 10 or so seconds. each can last from 3 - 8 seconds long.

When at 0, say: I need water, Now! (at this point, the player will slowly start taking damage, until they die. Damage in half a heart every 5 seconds)

If a player drinks a water bottle, reset their thirst meter" is mainly the main functions, but tons of configuration is included for these values in the config file!

<h2>Permissions</h2>
None yet.

<h2>Commands</h2>
None yet.

<h2>Config</h2>
```YAML
Level: 30
Time: 3 # Time in seconds interval in which your level should go down each (Time variable).
DamagePerZeroThirst: 1 # (In half hearts).
Warnings:
  FiveThirstLeft: '&cI need to find water!'
  OneThirstLeft: '&kI need water now!'
  ResetLevel: '&cYour thirst has been quenched! :)'
Potion:
  PotionEffectTypeByNameAddedOnZeroThirst: 'Confusion'
  Duration: 5 # (In seconds).
  Amplifier: 0
```  
