
import java.io.*;
import javax.swing.JOptionPane;
public class Controlador {

static BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in) );
	public static void main(String[] args)throws Exception
	{

		ArbolAVL obj=new ArbolAVL();
		int opc;
		
		int elem;
	do
	{
		System.out.println("-----ARBOLESAVL Hecho Por Juan Antonio Juarez Olivera------");
		System.out.println("Elija la opcion que desea:");	
		System.out.println();
		System.out.println("1.-Ingresar un elemento");
		System.out.println("2.-Buscar un elemento");
		System.out.println("3.-Eliminar un  elemento");
		System.out.println("4.-Imprimir ( E-Norden  Pre-Orden   Post-Orden )");
		System.out.println("5.- Salir");		
		opc=Integer.parseInt(entrada.readLine());
		
		switch(opc)
		{
				case 1:
				{
					System.out.println("Ingrese un elemento");
					elem=Integer.parseInt(entrada.readLine());
					obj.alta(elem);
					break;
				}
				case 2:
				{
					System.out.println("Ingrese elemento a buscar ");
					elem=Integer.parseInt(entrada.readLine());
					if(obj.buscar(elem))
					{
						System.out.println("El elemento se encuentra en el arbol ");
					}
					else
						System.out.println("El elemento NO se encuentra en el arbol ");
					break;
				}
				case 3:
				{
					System.out.println("Ingrese elemento a eliminar ");
					elem=Integer.parseInt(entrada.readLine());
					obj.eliminarelemento(elem);
					
					break;
				}
				case 4:
				{
					
					System.out.println("Imprimir  En orden  Pre Orden  ");
					obj.visualizar();
					System.out.println("\n");
					
					
					break;
				}
				
				case 5:
				{
					System.out.println("Salir del Programa");
					System.exit(0);
					break;
				}
		}
	
	}while(opc!=5);
	
	}
	}