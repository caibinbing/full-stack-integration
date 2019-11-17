import tkinter as tk

window = tk.Tk()

window.title('My window')

window.geometry('800x600')

tk.Label(window,text = 'P1', font = ('Arial',20),).place(x = 50 , y = 500 , anchor = 'nw') # y : down plus

window.mainloop()
 
