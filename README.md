# 新玩家歡迎插件 (NewPlayerWelcome)

這是一個 Minecraft 服務器插件，用於自動歡迎新玩家並給予新手裝備。

## 功能特點

### 🎉 自動歡迎系統
- 新玩家加入時自動發送歡迎消息
- 支持多條歡迎消息
- 可選擇廣播到全服或只發送私人消息
- 支持顏色代碼和變數替換

### 🛡️ 新手裝備系統
- 自動給予新玩家完整的起始裝備
- 包含武器、防具、工具、食物等
- 可自定義裝備內容和數量
- 支持背包滿時的處理
- **新增：支持 NBT 標籤、附魔和命名**

### ⚙️ 靈活配置
- 完整的配置文件系統
- 可自定義歡迎消息
- 可調整新手裝備內容
- 支持首次加入檢測
- **新增：NBT 標籤配置選項**

## 安裝方法

### 前置要求
- Minecraft 服務器 (Spigot/Paper 1.19+)
- Java 8 或更高版本

### 安裝步驟
1. 下載插件 JAR 文件
2. 將 JAR 文件放入服務器的 `plugins` 資料夾
3. 重啟服務器或重載插件
4. 插件會自動生成配置文件
5. 根據需要修改 `config.yml` 文件

## 編譯方法

如果您想從源碼編譯插件：

```bash
# 克隆項目
git clone [項目地址]

# 進入項目目錄
cd new-player-welcome

# 使用 Maven 編譯
mvn clean package

# 編譯完成後，JAR 文件會在 target 資料夾中
```

## 配置文件

### config.yml 主要設置

```yaml
# 歡迎消息設置
welcome:
  enabled: true                    # 是否啟用歡迎消息
  messages:                        # 歡迎消息列表
    - "&a歡迎 &e%player% &a來到服務器！"
    - "&6祝您在服務器玩得開心！"
  broadcast: true                  # 是否廣播到全服
  private_message: true            # 是否發送私人消息

# 新手裝備設置
starter_kit:
  enabled: true                    # 是否啟用新手裝備
  items:                           # 裝備列表
    - "DIAMOND_SWORD:1:0:name=&b&l新手鑽石劍:lore=&7這是一把強大的新手武器|&7祝您遊戲愉快:enchantments=SHARPNESS:5,DURABILITY:3,LOOTING:2"
    - "DIAMOND_HELMET:1:0:name=&b&l新手頭盔:lore=&7保護您的頭部|&7新手專用裝備:enchantments=PROTECTION:4,UNBREAKING:3,THORNS:2"

# 首次加入設置
first_join:
  only_first_join: true            # 是否只給首次加入的玩家裝備
  save_join_record: true           # 是否保存玩家加入記錄

# NBT 標籤設置
nbt:
  enabled: true                    # 是否啟用 NBT 標籤
  enchantments: true               # 是否啟用附魔
  naming: true                     # 是否啟用命名
  lore: true                       # 是否啟用描述
```

### 物品格式說明

#### 基本格式
物品格式為：`物品ID:數量:數據值`

#### 高級格式（支持 NBT）
物品格式為：`物品ID:數量:數據值:name=名稱:lore=描述1|描述2:enchantments=附魔1:等級1,附魔2:等級2`

**參數說明：**
- `物品ID`: Minecraft 物品的英文名稱 (如 DIAMOND_SWORD)
- `數量`: 物品數量
- `數據值`: 物品的數據值 (通常為 0)
- `name`: 物品的自定義名稱 (支持顏色代碼)
- `lore`: 物品的描述文本 (用 | 分隔多行，支持顏色代碼)
- `enchantments`: 附魔列表 (用逗號分隔，每個附魔用冒號分隔名稱和等級)

**示例：**
```yaml
# 帶有 NBT 標籤的鑽石劍
- "DIAMOND_SWORD:1:0:name=&b&l新手鑽石劍:lore=&7這是一把強大的新手武器|&7祝您遊戲愉快:enchantments=SHARPNESS:5,DURABILITY:3,LOOTING:2"

# 帶有 NBT 標籤的弓
- "BOW:1:0:name=&a&l新手弓:lore=&7精準的遠程武器|&7適合新手使用:enchantments=POWER:3,INFINITY:1,UNBREAKING:2"
```

## 命令

### `/welcome [玩家名稱]`
- 手動發送歡迎消息
- 不帶參數：向自己發送
- 帶玩家名稱：向指定玩家發送
- 權限：`newplayerwelcome.welcome`

### `/testnbt [物品字符串]`
- 測試 NBT 功能
- 不帶參數：測試預設的 NBT 物品
- 帶物品字符串：測試自定義 NBT 物品
- 權限：`newplayerwelcome.testnbt`

## 權限節點

- `newplayerwelcome.welcome` - 允許使用 `/welcome` 命令 (默認：OP)
- `newplayerwelcome.bypass` - 跳過新手裝備給予 (默認：OP)
- `newplayerwelcome.testnbt` - 允許使用 `/testnbt` 命令 (默認：OP)

## 事件

插件會監聽以下事件：
- `PlayerJoinEvent` - 玩家加入服務器

## 自定義歡迎消息

您可以在配置文件中自定義歡迎消息，支持以下功能：

### 顏色代碼
- `&a` - 綠色
- `&b` - 淺藍色
- `&c` - 紅色
- `&d` - 淺紫色
- `&e` - 黃色
- `&f` - 白色
- `&0` - 黑色
- `&1` - 深藍色
- `&2` - 深綠色
- `&3` - 深青色
- `&4` - 深紅色
- `&5` - 深紫色
- `&6` - 金色
- `&7` - 灰色
- `&8` - 深灰色
- `&9` - 藍色

### 變數替換
- `%player%` - 玩家名稱

## NBT 標籤功能

### 支持的附魔
- `SHARPNESS` - 鋒利
- `DURABILITY` - 耐久
- `LOOTING` - 搶奪
- `POWER` - 力量
- `INFINITY` - 無限
- `UNBREAKING` - 不毀
- `PROTECTION` - 保護
- `THORNS` - 荊棘
- `FEATHER_FALLING` - 摔落保護
- `EFFICIENCY` - 效率
- `SILK_TOUCH` - 絲綢之觸

### 顏色代碼在 NBT 中的使用
- 在 `name` 和 `lore` 中可以使用顏色代碼
- 使用 `&` 符號作為顏色代碼前綴
- 支持所有標準的 Minecraft 顏色代碼

## 故障排除

### 常見問題

**Q: 插件無法載入**
A: 檢查服務器版本是否兼容，確保使用 Java 8 或更高版本

**Q: 新手裝備沒有給予**
A: 檢查配置文件中 `starter_kit.enabled` 是否為 true

**Q: 歡迎消息沒有顯示**
A: 檢查配置文件中 `welcome.enabled` 是否為 true

**Q: NBT 標籤沒有生效**
A: 檢查配置文件中 `nbt.enabled` 是否為 true

**Q: 附魔沒有應用**
A: 檢查配置文件中 `nbt.enchantments` 是否為 true

**Q: 權限問題**
A: 確保玩家有相應的權限節點

## 技術支持

如果您遇到問題或需要幫助，請：
1. 檢查服務器控制台的錯誤信息
2. 確認配置文件格式正確
3. 檢查權限設置
4. 查看插件日誌

## 版本歷史

- **v1.1.0** - NBT 功能版本
  - 新增 NBT 標籤支持
  - 新增附魔系統
  - 新增物品命名功能
  - 新增物品描述功能
  - 新增測試 NBT 命令
- **v1.0.0** - 初始版本
  - 基本歡迎系統
  - 新手裝備系統
  - 配置文件支持
  - 權限系統

## 授權

此插件採用 MIT 授權條款。

## 貢獻

歡迎提交 Issue 和 Pull Request 來改進這個插件！
