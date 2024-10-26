##
 # advancement.mcfunction
 # 
 #
 # Created by MAT06mat
##
tellraw @s ["",{"text":"You made a ","color":"light_purple"},{"text":"new advancement ","color":"aqua"},{"text":"!","color":"light_purple"}]
playsound minecraft:entity.player.levelup
scoreboard players add @s Res 5
execute store result storage minecraft:pack res int 1 run scoreboard players get @s Res
execute as @s at @s run function pack:set_res with storage minecraft:pack