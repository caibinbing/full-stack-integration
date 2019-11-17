import tkinter as tk
import os
import tkinter.messagebox

window = tk.Tk()

window.title('Welcome to choibunbing\'s website')

window.geometry('550x370')

# 使用画布
canvas = tk.Canvas(window, bg = 'green' , width = 550 , height = 150)
canvas.pack()
# 画布画图
logo = tk.PhotoImage(file = 'github_logo_128.png')
image = canvas.create_image(270,10,anchor = 'n' , image = logo)

welcome_label = tk.Label(window, text = 'Welcome', font=('Courier New',14)).pack()
username_label = tk.Label(window, text = 'User Name: ' , font=('Courier New',14)).place(x = 100 , y = 200)
# 需要绑定参数
email = tk.StringVar()
username_text = tk.Entry(window,show=None,textvariable = email).place(x = 275 , y = 203)
password_label = tk.Label(window, text = 'Password: ', font = ('Courier New',14)).place(x = 100 , y = 250)
password = tk.StringVar()
password_text = tk.Entry(window,show='*',textvariable = password).place(x = 275 , y = 253)

filePath = 'sn.txt'
def login():
    # 如果账户已经存在，可以直接登录
    if not(os.path.exists(filePath) and os.path.isfile(filePath)):
        f = open(filePath,'w')
        f.close()
    isUser = False
    em = email.get()    # 获取email参数
    psw = password.get()    # 获取password参数
    with open(filePath,'r') as f:
        for line in f.readlines():
            strs = line[:-1].split(':')    # 注意：读取的最后一位是\n
            e = strs[0]
            p = strs[1]
            if( e == em and p == psw):
                isUser = True
                break
    if isUser:
        tkinter.messagebox.showinfo(title = 'Success' , message = 'Welcome! My Lord.')
    else:
        tkinter.messagebox.showerror(title = 'Error' , message = 'User Not Exists or Incorrect input!')    

def newWindow():
    def signUp():
        if not(os.path.exists(filePath) and os.path.isfile(filePath)):
            f = open(filePath,'w')
            f.close()
        em = usrn.get()
        psw = pswd.get()
        cfm = comfirm.get()
        if psw != cfm:
            tkinter.messagebox.showerror(title = 'Error' , message = 'Password not match!')
            return
        with open(filePath,'a+') as f:
            f.seek(0)    # 跳到文件头
            data = f.readlines()
            for line in data:
                if(em == line.split(':')[0]):
                    tkinter.messagebox.showerror(title = 'Error' , message = 'User Exists!')
                    return
            f.write('%s:%s\n'%(em,psw))
        tkinter.messagebox.showinfo(title = 'Success' , message = 'Successfully Registered!')
        
    # 点击注册进去注册页面，输入注册信息
    subWindow = tk.Toplevel(window)
    subWindow.title('Register')
    subWindow.geometry('400x250')

    usrn_label = tk.Label(subWindow,text = 'User name: ',font = ('Courier New',12)).place(x = 20,y = 30)
    usrn = tk.StringVar()
    usrn_text = tk.Entry(subWindow, show = None,textvariable = usrn).place(x = 200 , y = 33)
    pswd_label = tk.Label(subWindow, text = 'Password: ' , font = ('Courier New',12)).place(x = 20 , y = 80)
    pswd = tk.StringVar()
    pswd_text = tk.Entry(subWindow , show = '*',textvariable = pswd).place(x = 200 , y = 83)
    comfirm_label = tk.Label(subWindow, text = 'Comfirm Password: ' , font = ('Courier New',12)).place(x = 20 , y = 130)
    comfirm = tk.StringVar()
    comfirm_text = tk.Entry(subWindow , show = '*' , textvariable = comfirm).place(x = 200 , y = 133)
    
    tk.Button(subWindow,text = 'Sign Up' , font = ('Courier New',10),command = signUp).place(x = 250 , y = 180)
    # 确定后便可以返回登录界面进行登录
    
    pass

# 怎么绑定参数到按钮？答：get()方法
login_button = tk.Button(window, text = 'Login',command = login).place(x = 190 , y = 300)
signup_button = tk.Button(window,text = 'Sign Up',command = newWindow).place(x = 300 , y = 300)

window.mainloop()
