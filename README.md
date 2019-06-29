# X-IM 即时通信助手

X-IM是一个基于Netty的即时通信系统，适用于在

| 命令      | 描述                             | 示例                       |
| --------- | -------------------------------- | -------------------------- |
| `:login`  | 使用用户名和和密码登录           | `:login` username pwd      |
| `:logout` | 离线                             | `:logout`                  |
| `:cg`     | `create group`, 创建群           | `:cg` group_name           |
| `:jg`     | `join group`, 加入群             | `:jg` group_name           |
| `:qg`     | `quict group`, 退出群            | `:qg` group_name           |
| `:lqm`    | `list group members`，查看群成员 | `lqm` group_name           |
| `:gc`     | `group chat`，群聊               | `:gc` group_name input_msg |
| `:pc`     | `private chat`，私聊             | `:pc` username             |

