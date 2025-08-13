# NBT 標籤功能使用說明

## 概述

新玩家歡迎插件現在支持 NBT 標籤，可以為新手裝備添加：
- 自定義名稱（支持顏色代碼）
- 自定義描述（支持多行和顏色代碼）
- 附魔效果
- 其他 NBT 屬性

## 基本語法

### 物品格式
```
物品ID:數量:數據值:name=名稱:lore=描述1|描述2:enchantments=附魔1:等級1,附魔2:等級2
```

### 參數說明
- `物品ID`: Minecraft 物品的英文名稱
- `數量`: 物品數量
- `數據值`: 物品的數據值（通常為 0）
- `name`: 物品的自定義名稱
- `lore`: 物品的描述文本（用 | 分隔多行）
- `enchantments`: 附魔列表（用逗號分隔，每個附魔用冒號分隔名稱和等級）

## 顏色代碼

在 `name` 和 `lore` 中可以使用以下顏色代碼：

| 代碼 | 顏色 | 代碼 | 顏色 |
|------|------|------|------|
| `&a` | 綠色 | `&f` | 白色 |
| `&b` | 淺藍色 | `&0` | 黑色 |
| `&c` | 紅色 | `&1` | 深藍色 |
| `&d` | 淺紫色 | `&2` | 深綠色 |
| `&e` | 黃色 | `&3` | 深青色 |
| `&4` | 深紅色 | `&5` | 深紫色 |
| `&6` | 金色 | `&7` | 灰色 |
| `&8` | 深灰色 | `&9` | 藍色 |

### 特殊格式代碼
- `&l` - 粗體
- `&n` - 下劃線
- `&o` - 斜體
- `&m` - 刪除線
- `&k` - 隨機字符

## 支持的附魔

### 武器附魔
- `SHARPNESS` - 鋒利（增加攻擊傷害）
- `DURABILITY` - 耐久（減少耐久度消耗）
- `LOOTING` - 搶奪（增加掉落物）
- `FIRE_ASPECT` - 火焰附加（點燃敵人）
- `KNOCKBACK` - 擊退（增加擊退距離）

### 弓附魔
- `POWER` - 力量（增加箭矢傷害）
- `INFINITY` - 無限（箭矢不會消耗）
- `UNBREAKING` - 不毀（減少耐久度消耗）
- `FLAME` - 火焰（箭矢點燃敵人）
- `PUNCH` - 衝擊（增加擊退距離）

### 防具附魔
- `PROTECTION` - 保護（減少所有傷害）
- `FIRE_PROTECTION` - 火焰保護（減少火焰傷害）
- `BLAST_PROTECTION` - 爆炸保護（減少爆炸傷害）
- `PROJECTILE_PROTECTION` - 彈射物保護（減少彈射物傷害）
- `THORNS` - 荊棘（攻擊者受到傷害）
- `FEATHER_FALLING` - 摔落保護（減少摔落傷害）

### 工具附魔
- `EFFICIENCY` - 效率（增加挖掘速度）
- `SILK_TOUCH` - 絲綢之觸（挖掘時掉落原方塊）
- `FORTUNE` - 時運（增加掉落物數量）

## 配置示例

### 基礎武器
```yaml
# 鑽石劍 - 鋒利 V，耐久 III，搶奪 II
- "DIAMOND_SWORD:1:0:name=&b&l新手鑽石劍:lore=&7這是一把強大的新手武器|&7祝您遊戲愉快:enchantments=SHARPNESS:5,DURABILITY:3,LOOTING:2"

# 弓 - 力量 III，無限，不毀 II
- "BOW:1:0:name=&a&l新手弓:lore=&7精準的遠程武器|&7適合新手使用:enchantments=POWER:3,INFINITY:1,UNBREAKING:2"
```

### 防具套裝
```yaml
# 鑽石頭盔
- "DIAMOND_HELMET:1:0:name=&b&l新手頭盔:lore=&7保護您的頭部|&7新手專用裝備:enchantments=PROTECTION:4,UNBREAKING:3,THORNS:2"

# 鑽石胸甲
- "DIAMOND_CHESTPLATE:1:0:name=&b&l新手胸甲:lore=&7保護您的身體|&7新手專用裝備:enchantments=PROTECTION:4,UNBREAKING:3,THORNS:2"

# 鑽石護腿
- "DIAMOND_LEGGINGS:1:0:name=&b&l新手護腿:lore=&7保護您的腿部|&7新手專用裝備:enchantments=PROTECTION:4,UNBREAKING:3,THORNS:2"

# 鑽石靴子
- "DIAMOND_BOOTS:1:0:name=&b&l新手靴子:lore=&7保護您的腳部|&7新手專用裝備:enchantments=PROTECTION:4,UNBREAKING:3,FEATHER_FALLING:4"
```

### 工具套裝
```yaml
# 鑽石鎬
- "DIAMOND_PICKAXE:1:0:name=&6&l新手鎬子:lore=&7快速挖掘工具|&7新手專用:enchantments=EFFICIENCY:5,UNBREAKING:3,SILK_TOUCH:1"

# 鑽石斧
- "DIAMOND_AXE:1:0:name=&6&l新手斧頭:lore=&7快速砍伐工具|&7新手專用:enchantments=EFFICIENCY:5,UNBREAKING:3,LOOTING:2"

# 鑽石鏟
- "DIAMOND_SHOVEL:1:0:name=&6&l新手鏟子:lore=&7快速挖掘工具|&7新手專用:enchantments=EFFICIENCY:5,UNBREAKING:3,SILK_TOUCH:1"
```

### 食物和消耗品
```yaml
# 熟牛肉
- "COOKED_BEEF:16:0:name=&6&l美味熟牛肉:lore=&7補充飢餓值|&7新手專用食物"

# 金蘋果
- "GOLDEN_APPLE:3:0:name=&6&l金蘋果:lore=&7提供額外效果|&7新手專用"

# 火把
- "TORCH:32:0:name=&e&l火把:lore=&7照亮黑暗|&7新手專用"
```

## 測試命令

### `/testnbt` 命令
使用此命令可以測試 NBT 功能：

```bash
# 測試預設的 NBT 物品
/testnbt

# 測試自定義 NBT 物品
/testnbt DIAMOND_SWORD:1:0:name=&b&l測試劍:lore=&7測試物品:enchantments=SHARPNESS:5
```

## 注意事項

### 1. 附魔等級限制
- 大多數附魔的最高等級為 5
- 某些特殊附魔（如無限、絲綢之觸）最高等級為 1
- 超過限制的等級會被自動調整

### 2. 兼容性
- 確保服務器版本支持所使用的附魔
- 某些新版本的附魔在舊版本中可能無法使用

### 3. 性能考慮
- 複雜的 NBT 標籤會增加物品創建的時間
- 建議不要為每個物品都添加過多的 NBT 標籤

### 4. 錯誤處理
- 如果 NBT 標籤格式錯誤，插件會記錄警告並使用預設物品
- 檢查服務器日誌以獲取詳細的錯誤信息

## 故障排除

### 常見問題

**Q: 附魔沒有生效**
A: 檢查附魔名稱是否正確，確保使用大寫字母

**Q: 顏色代碼沒有顯示**
A: 確保使用 `&` 符號作為顏色代碼前綴

**Q: 描述文本沒有換行**
A: 使用 `|` 符號分隔多行描述

**Q: 物品無法創建**
A: 檢查物品 ID 是否正確，確保服務器支持該物品

## 進階用法

### 組合多個屬性
```yaml
# 複雜的 NBT 物品
- "DIAMOND_SWORD:1:0:name=&b&l&l傳說之劍:lore=&7這是一把傳說中的武器|&7擁有強大的力量|&c&l警告：使用時請小心:enchantments=SHARPNESS:5,DURABILITY:3,LOOTING:3,FIRE_ASPECT:2"
```

### 創建主題套裝
```yaml
# 火焰主題套裝
- "DIAMOND_SWORD:1:0:name=&c&l火焰劍:lore=&7燃燒一切敵人:enchantments=SHARPNESS:5,FIRE_ASPECT:2"
- "DIAMOND_HELMET:1:0:name=&c&l火焰頭盔:lore=&7火焰保護:enchantments=FIRE_PROTECTION:4,UNBREAKING:3"
```

### 創建特殊物品
```yaml
# 幸運工具
- "DIAMOND_PICKAXE:1:0:name=&a&l幸運鎬:lore=&7挖掘時有機會獲得額外掉落物:enchantments=EFFICIENCY:5,FORTUNE:3,UNBREAKING:3"
```

## 總結

NBT 標籤功能讓新手裝備更加豐富和個性化，您可以：
1. 為每個物品添加獨特的名稱和描述
2. 應用合適的附魔來提升物品性能
3. 創建主題套裝來增強遊戲體驗
4. 使用顏色代碼讓物品更加美觀

通過合理配置 NBT 標籤，您可以為新玩家提供既實用又美觀的起始裝備！
