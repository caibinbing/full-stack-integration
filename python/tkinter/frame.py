import tkinter as tk

window = tk.Tk()

window.title('My TK')

window.geometry('800x600')

tk.Label(window , text = 'on the window' , bg = 'red' , font=('Arial',16)).pack()

frame = tk.Frame(window)
frame.pack()

frame_1 = tk.Frame(frame)
frame_r = tk.Frame(frame)
frame_1.pack(side = 'left')
frame_r.pack(side = 'right')

tk.Label(frame_1 , text = 'on the frame_l1', bg = 'green').pack()
tk.Label(frame_1 , text = 'on the frame_l2', bg = 'green').pack()
tk.Label(frame_1 , text = 'on the frame_l3', bg = 'green').pack()
tk.Label(frame_r , text = 'on the frame_r1', bg = 'yellow').pack()
tk.Label(frame_r , text = 'on the frame_r2', bg = 'yellow').pack()
tk.Label(frame_r , text = 'on the frame_r3', bg = 'yellow').pack()
window.mainloop()
