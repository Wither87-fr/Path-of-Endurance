##
 # init.mcfunction
 # 
 #
 # Created by MAT06mat
##
tellraw @a ["",{"text":"Datapack has been reload !","color":"light_purple"}]
tellraw @a ["",{"text":"/!\\ All advancements revoked !","color":"light_purple"}]
advancement revoke @a everything

scoreboard objectives add Res dummy