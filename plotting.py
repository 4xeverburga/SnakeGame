from tkinter import *

ventana = Tk()
ventana.title("Juego de la serpiente XD")
ventana.geometry("200x100")

def iniciar():
    print("inicia")
    pass

button_inicio = Button(ventana, text="iniciar")
button_inicio.grid(row=0, column=0)

ventana.mainloop()
