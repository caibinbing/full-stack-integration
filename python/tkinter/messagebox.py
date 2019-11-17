import tkinter as tk
import tkinter.messagebox

window = tk.Tk()

window.title('My Window')

window.geometry('800x600')

def hit_me():
    # tkinter.messagebox.showinfo(title='Hi',message='hello')
    # tkinter.messagebox.showerror(title = 'Hi' , message = 'error')
    # tkinter.messagebox.showwarning(title = 'Hi' , message = 'warning')
    # print(tkinter.messagebox.askquestion(title = 'Hi' , message = 'hello')) # return yes or no
    # print(tkinter.messagebox.askyesno(title = 'Hi' , message = 'Hello'))  # return true or false
    print(tkinter.messagebox.askokcancel(title = 'Hi' , message = 'hello'))   # return true or false

tk.Button(window,text = 'hit me' , bg = 'green' , font=('Arial',14),command = hit_me).pack()

window.mainloop()
