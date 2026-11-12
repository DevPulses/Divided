import struct
import zlib

def create_png(width, height, pixels, output_path):
    raw_data = bytearray()
    for y in range(height):
        raw_data.append(0)
        for x in range(width):
            val = pixels[y][x]
            r, g, b, a = val if isinstance(val, (tuple, list)) else (0, 0, 0, 0)
            raw_data.extend([int(r), int(g), int(b), int(a)])
    compressed = zlib.compress(bytes(raw_data))
    
    def make_chunk(chunk_type, data):
        length = len(data)
        crc = zlib.crc32(chunk_type + data) & 0xffffffff
        return struct.pack('>I', length) + chunk_type + data + struct.pack('>I', crc)
        
    out = bytearray(b'\x89PNG\r\n\x1a\n')
    out.extend(make_chunk(b'IHDR', struct.pack('>IIBBBBB', width, height, 8, 6, 0, 0, 0)))
    out.extend(make_chunk(b'IDAT', compressed))
    out.extend(make_chunk(b'IEND', b''))
    
    with open(output_path, 'wb') as f:
        f.write(out)

armor_dir = "/home/loth/Projects/Divided/src/main/resources/assets/divided/textures/models/armor"
_ = (0, 0, 0, 0)

# ==========================================
# 1. TINFOIL LAYER 1 (Шлем: x=0..32, y=0..16)
# ==========================================
# Серебристо-белая мятая фольга для шлема
tinfoil_l1 = [[_ for _ in range(64)] for _ in range(32)]
# Верхняя грань шлема (x=8..16, y=0..8)
for y in range(0, 8):
    for x in range(8, 16):
        tinfoil_l1[y][x] = (240, 245, 255, 255) if (x+y)%2==0 else (210, 220, 235, 255)
# Бока шлема (x=0..32, y=8..16)
for y in range(8, 16):
    for x in range(0, 32):
        # Серебристые грани скомканной фольги
        tinfoil_l1[y][x] = (255, 255, 255, 255) if (x*3+y*7)%5==0 else (180, 195, 210, 255)

create_png(64, 32, tinfoil_l1, f"{armor_dir}/tinfoil_layer_1.png")

# ==========================================
# 2. DADS_SUIT LAYER 1 (Майка + Тапочки)
# ==========================================
dads_l1 = [[_ for _ in range(64)] for _ in range(32)]
# Тело майки (грудь и спина: x=16..40, y=16..32)
for y in range(16, 32):
    for x in range(16, 40):
        dads_l1[y][x] = (250, 250, 255, 255) if x%2==0 else (225, 230, 240, 255)
# Пятно от борща на животе (x=22..24, y=26..28)
for y in range(26, 29):
    for x in range(22, 25):
        dads_l1[y][x] = (200, 150, 80, 255)
# Тапочки (ботинки на ногах)
for y in range(24, 32):
    for x in range(0, 16): # левый ботинок
        dads_l1[y][x] = (160, 110, 70, 255)
create_png(64, 32, dads_l1, f"{armor_dir}/dads_suit_layer_1.png")

# ==========================================
# 3. DADS_SUIT LAYER 2 (Треники с полосками: поножи)
# ==========================================
dads_l2 = [[_ for _ in range(64)] for _ in range(32)]
for y in range(16, 32):
    for x in range(0, 32):
        # Темно-синяя ткань
        dads_l2[y][x] = (35, 45, 65, 255)
    # Белые лампасы по внешним сторонам штанин
    dads_l2[y][0] = (255, 255, 255, 255)
    dads_l2[y][1] = (255, 255, 255, 255)
    dads_l2[y][16] = (255, 255, 255, 255)
    dads_l2[y][17] = (255, 255, 255, 255)
create_png(64, 32, dads_l2, f"{armor_dir}/dads_suit_layer_2.png")

# ==========================================
# 4. MOMS_ROBE LAYER 1 (Леопардовый халат)
# ==========================================
moms_l1 = [[_ for _ in range(64)] for _ in range(32)]
for y in range(16, 32):
    for x in range(16, 40): # грудь и подол
        # Леопардовый принт
        if (x*7 + y*13) % 6 == 0:
            moms_l1[y][x] = (60, 30, 15, 255) # пятно
        else:
            moms_l1[y][x] = (235, 175, 75, 255) # рыжий шелк
# Розово-малиновый пояс на талии
for x in range(16, 40):
    moms_l1[24][x] = (210, 30, 90, 255)
    moms_l1[25][x] = (210, 30, 90, 255)
# Золотая пряжка
moms_l1[24][27] = (255, 215, 0, 255)
moms_l1[25][27] = (255, 215, 0, 255)
create_png(64, 32, moms_l1, f"{armor_dir}/moms_robe_layer_1.png")

print("Created 3D entity armor skins!")
