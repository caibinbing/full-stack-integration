from PIL import Image,ImageDraw,ImageFont
import pandas as pd
import tkinter.messagebox
import logging
import sys
import argparse
import qrcode
import os


def watermarking(inputDir, outputDir, picName, text, position, logo, qrCode, color):
    # 原始图
    image = Image.open(inputDir + picName + '.jpg')
    logoPosition, qrCodePosition = getImgPosition(image.size[0], image.size[1], position)
    # 水印：Logo + 二维码 + 款号
    mark = Image.open(logo)
    qrcode = Image.open(qrCode)
    # 新图层
    layer = Image.new('RGBA' , image.size , (0,0,0,0))
    # 贴Logo
    layer.paste(mark , logoPosition)
    # 贴二维码
    layer.paste(qrcode , qrCodePosition)
    # 组合
    out = Image.composite(layer , image , layer)
    temp = 'temp/'
    if not os.path.exists(temp):
        os.makedirs(temp)
    out.save(temp + picName + 'out.png')
    outImg = Image.open(temp + picName + 'out.png').convert('RGBA')
    # 新图层
    txt = Image.new('RGBA', outImg.size, (0,0,0,0))
    # 设置文字
    font_size = 22
    fnt = ImageFont.truetype('C:\Windows\Fonts\simsun.ttc', font_size)
    d = ImageDraw.Draw(txt)
    for i, item in enumerate(text):
        textPosition = getTextPosition(txt.size[0], txt.size[1], position, i, font_size)
        d.text(textPosition, item, font = fnt, fill = 'black' if color[0] == '黑' else 'white')
        result = Image.alpha_composite(outImg, txt)
    # 水印图
    if not os.path.exists(outputDir):
        os.makedirs(outputDir)
    result.save(outputDir + picName + '.png')


def getImgPosition(x, y, position):
    if position[0:2] == '左上':
        logoPosition =(x - 1000, y - 1560)
        qrCodePosition =(x - 990, y - 1500)
    elif position[0:2] == '左下':
        logoPosition =(x - 1000, 9 * y // 10 - 220)
        qrCodePosition =(x - 990, 9 * y // 10 - 160)
    elif position[0:2] == '右上':
        logoPosition =(x - 240, y - 1560)
        qrCodePosition =(x - 230, y - 1500)
    elif position[0:2] == '右下':
        logoPosition =(x - 240, 9 * y // 10 - 220)
        qrCodePosition =(x - 230, 9 * y // 10 - 160)
    return logoPosition, qrCodePosition

def getTextPosition(x, y, position, i, font_size):
    if position[0:2] == '左上':
        textPosition = (70, 5 * y // 28 + float(i) * font_size * 1.5)
    elif position[0:2] == '左下':
        textPosition = (70, 9 * y // 10 + float(i) * font_size * 1.5)
    elif position[0:2] == '右上':
        textPosition = (x - 250, 5 * y // 28 + float(i) * font_size * 1.5)
    elif position[0:2] == '右下':
        textPosition = (x - 250, 9 * y // 10 + float(i) * font_size * 1.5)
    return textPosition
    

def main(args):
    # 读取excel内容
    try:
        dataFrame = pd.read_excel(args.xlsx)
    except FileNotFoundError:
        logging.error('File Not Found Error!')
        return
    qrCodeURLPrefix = 'https://mp.eekamclub.com/yjshow2/usrapi?filename='
    print('Processing',end = '')
    # 遍历数据
    for index in dataFrame.index.values:
        # 图片名
        picName = dataFrame.iloc[index , 0] # loc指定k值的行，iloc第i行
        # 款号
        text = dataFrame.iloc[index, 1].split('\n')
        # 品牌
        brandCode = dataFrame.iloc[index, 5]
        # 位置
        position = dataFrame.iloc[index, 6]
        # 颜色
        color = dataFrame.iloc[index, 7]
        # Logo
        logo = brandCode + 'logo' + color[0] + '.png'
        # 二维码
        qr = qrcode.QRCode(
                version = 1,
                error_correction = qrcode.constants.ERROR_CORRECT_L,
                box_size = 4,
                border = 2,
            )
        qr.add_data(qrCodeURLPrefix + picName + '.jpg;')
        qrCode = qr.make_image()
        qrCodePath = args.qrcode_dir + picName + '_qrcode.png'
        if not os.path.exists(args.qrcode_dir):
            os.makedirs(args.qrcode_dir)
        qrCode.save(qrCodePath)
        watermarking(inputDir = args.input_dir, outputDir = args.output_dir, picName = picName, text = text,
                     position = position, logo = logo, qrCode = qrCodePath, color=color)
        print('.',end = '')

def parse_arguments(argv):
    parser = argparse.ArgumentParser()
    parser.add_argument('xlsx' , type = str , help = 'Could be a excel (.xlsx or .xls) file.')
    parser.add_argument('input_dir', type = str , help = 'Directory with original images')
    parser.add_argument('output_dir', type = str , help = 'Directory with processed images.')
    parser.add_argument('qrcode_dir', type = str, help = 'Directory with QRCode.')
    return parser.parse_args(argv)

if __name__ == '__main__':
    sys.exit(main(parse_arguments(sys.argv[1:])))
