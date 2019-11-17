import tkinter as tk

window = tk.Tk()

window.geometry('800x600')

window.title('My TK')

canvas = tk.Canvas(window,bg = 'green',height = 550 , width = 512)
canvas.pack()

image_file = tk.PhotoImage(file = 'Lenna.jpg')

image = canvas.create_image(250,0,anchor = 'n',image = image_file)

x0,y0,x1,y1 = 100, 100 , 150 , 150

line = canvas.create_line(x0 - 50, y0 - 50 , x1 - 50 , y1 - 50 )
oval = canvas.create_oval(x0 + 120 , y0 + 50 , x1 + 120 , y1 + 50 , fill = 'yellow')
arc = canvas.create_arc(x0 , y0 + 50 , x1 ,y1 + 50 , start = 0, extent = 180)
rect = canvas.create_rectangle(330 , 30 , 330 + 20 , 30 + 20 )

def moveit():
    canvas.move(oval , 2 , 2 )

b = tk.Button(window , text = ' move item' , command = moveit).pack()
window.mainloop()
