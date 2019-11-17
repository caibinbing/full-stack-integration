import tkinter as tk

window = tk.Tk()

window.title('My Window')

window.geometry('800x600')

for i in range(3):
    for j in range(3):
        tk.Label(window,text = 1).grid(row = i , column = j , padx = 5, pady = 5 , ipadx = 5 , ipady = 5)

window.mainloop()
