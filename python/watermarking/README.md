# Watermarking
根据excel表格内容批量给图片添加水印

+ Operating parameters
  - xlsx: excel表的具体路径
  - input_dir: 待处理图片的文件夹
  - output_dir: 输出图片结果的文件夹
  - qrcode_dir: 二维码的存储文件夹
  - 以上均需以'/'结尾

+ Requirement
  - 读取excel表格内容，得到图片名，款号，品牌，水印位置等信息。
  - 根据图片名对对应的图片进行水印处理。
  - 根据款号选择正确的Logo图片，并添加Logo水印。
  - 根据水印位置在正确的位置上添加水印。
  - 水印内容包括：品牌Logo、小程序二维码、款号信息等。
  - 要求批量处理

+ Needed
  - pillow
  - pandas
  - xlrd
  - qrcode
  
+ 温馨提示
  - 本项目中二维码的内容可以直接跳转到小程序，具体地址可以根据需求在小程序后台自行配置。

+ FAQ
  - pandas如何读取excel表信息，具体到某行，某列？
  
  &emsp;&emsp;答：本项目采用pandas库进行操作。[参考答案](https://www.cnblogs.com/liulinghua90/p/9935642.html)
  
  - PIL的Image模块如何使用？
  
  &emsp;&emsp;答：[参考答案](https://www.cnblogs.com/way_testlife/archive/2011/04/20/2022997.html)
  
  - pip添加清华源？
  
  &emsp;&emsp;答：[清华pypi镜像使用帮助](https://mirrors.tuna.tsinghua.edu.cn/help/pypi/)
  
  - python如何添加log日志？
  
  &emsp;&emsp;答：
```python
    import logging
    logging.debug('Debugging information')
    logging.info('Informational message')
    logging.warning('Warning:config file %s not found', 'server.conf')
    logging.error('Error occurred')
    logging.critical('Critical error -- shutting down')
```

  - main()函数写法，以及运行参数用法？
  
  &emsp;&emsp;答：[参考答案一](https://anywugn.com/?p=1221) [参考答案二](https://github.com/davidsandberg/facenet/blob/master/src/align/align_dataset_mtcnn.py)
  
  - NameError: name 'raw_input' is not defined
  
  &emsp;&emsp;答：python3 只有input()方法
  
  - ImportError: Missing optional dependency 'xlrd'. Install xlrd >= 1.0.0 for Excel support Use pip or conda to install xlrd.
  
  &emsp;&emsp;答：安装xlrd
  
  - DeprecationWarning:.ix is deprecated. Please use .loc for label based indexing or .iloc for positional indexing
  
  &emsp;&emsp;答：使用loc()或者iloc()替换ix()方法。其中loc()和iloc()的区别是：loc()选取指定key的内容，iloc选取index对应的内容。
  
  - 如何传递列表参数？(如果是传递元组呢？字典呢？提示：*args and **args)
  
  &emsp;&emsp;答：可以使用默认参数list=[]，但是需要注意多次调用方法都是使用同一个数组，因为数组ID相同。方法中需要添加判断if not list:list = []
  
  - python如何生存二维码？
  
  &emsp;&emsp;答：[参考答案](https://www.jianshu.com/p/b4b14e314b2a) 需要注意的是，二维码的参数需要根据实际需求进行调整。
  
  - TypeError: can't multiply sequence by non-int of type 'float'
  
  &emsp;&emsp;答：传参出错，传递了string。检查实际开发传参是否都正确，检查不出来可以一个个参数强制转换进行排查。
  
  - python中如何同时获取序列的值和下标位置？
  
  &emsp;&emsp;答：enumerate(sequence, start = 0)
  
  - ValueError: image has wrong mode 和 OSError: cannot write mode RGBA as JPEG
  
  &emsp;&emsp;答：本项目采取的操作是，先将添加图片水印的结果保存，再取出.conver('RGBA')，最后添加文字水印再保存。
  
  - python ImageDraw.Draw中.text()方法中文乱码？
  
  &emsp;&emsp;答：更换字体为C:\Windows\Fonts\simsun.ttc。查询字体可以进目录对喜欢的字体右键查看属性。文件名不等于真实字体引用名。
  
  - 如何根据需求改变字体水印颜色？
  
  &emsp;&emsp;答：本文使用三元运算符，fill = 'black' if condition == true else 'white'。直接使用英文，免去设置四维颜色通道的苦恼。
