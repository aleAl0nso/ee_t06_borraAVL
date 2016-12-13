

class ArbolAVL
{
	
	
	Nodo nuevo, raiz, temp, temp2;
	int altura=0; int altura2=0;
	public ArbolAVL()
	{
		nuevo = null;
		raiz = null;
	}
	
	public void alta(int dato) 
	{
		if(raiz==null)					
		{
			raiz=new Nodo();
			raiz.dato=dato;
			raiz.drcha=null;
			raiz.izqda=null;
			raiz.padre=null;
			raiz.balance=0;
		}
		else						
		{
			temp=raiz;
			while(temp!=null)
			{
			
			if((dato>temp.dato)&&(temp.drcha==null))	
			{
				nuevo=new Nodo();
				nuevo.dato=dato;
				nuevo.izqda=null;
				nuevo.drcha=null;
				temp.drcha=nuevo;
				nuevo.padre=temp;
				nuevo.balance=0;
				balanceAlta(nuevo.padre,1);
				break;
			}
			//si el dato es menor y hay espacio
			else if ((dato<temp.dato)&&(temp.izqda==null))	
			{
				nuevo=new Nodo();
				nuevo.dato=dato;
				nuevo.izqda=null;
				nuevo.drcha=null;
				temp.izqda=nuevo;
				nuevo.padre=temp;
				nuevo.balance=0;
				balanceAlta(nuevo.padre,-1);
				break;
			}
			
			else if (dato==temp.dato)
			{
				
				break;
			}
				
			
			else if(dato>temp.dato)
				temp=temp.drcha;
			
			else
				temp=temp.izqda;
			}
		}
	}

	public void balanceAlta(Nodo nodo, int incremento)
	{	
		int tipo=-1;
		while(nodo!=null)
		{
			nodo.balance=nodo.balance+incremento; 
			if(nodo.balance==0) 
				break;
			else
			{
				tipo=tipoRotacion(nodo); 
				if(tipo==0) 
				{
					if(nodo.padre!=null)
					{
						if(nodo.dato<nodo.padre.dato)
						{
							incremento=-1;
						}
						else
						{
							incremento=1;
						}
					}
					nodo=nodo.padre;
				}
				if(tipo==1)	
					{		
						rotacionSI(nodo,nodo.drcha);
						break;
					}
				if(tipo==2) 
					{
						rotacionSD(nodo.drcha,nodo.drcha.izqda);
						rotacionSI(nodo,nodo.drcha);
						break;
					}
				if(tipo==3)
					{
						rotacionSD(nodo,nodo.izqda);


						break;
					}
				if(tipo==4) 
					{
					rotacionSI(nodo.izqda,nodo.izqda.drcha);
					rotacionSD(nodo,nodo.izqda);
					
						break;
					}
			
			}
			
			
		}
	}

	
	public int tipoRotacion(Nodo pr)
	{
		if(pr.balance==2)		
		{
			if((pr.drcha.balance==0)||(pr.drcha.balance==1))
				return 1;
			else if(pr.drcha.balance==-1)
				return 2;
		}
		else if(pr.balance==-2)		
		{
			if((pr.izqda.balance==0)||(pr.izqda.balance==-1))
				return 3;
			else if(pr.izqda.balance==1)
				return 4;
		}
		return 0;
	}
	
	
		public void rotacionSI(Nodo pr, Nodo hijo_der)
		{		
			int w, balt;
			temp=hijo_der.izqda;
			if(pr==raiz)
			{
				raiz=hijo_der;
				hijo_der.padre=null;
			}
			else
			{
				if(pr.dato>pr.padre.dato)
				{
					pr.padre.drcha=hijo_der;
				}
				else
				{
					pr.padre.izqda=hijo_der;
				}
				hijo_der.padre=pr.padre;
			}
			hijo_der.izqda=pr;
			pr.drcha=temp;
			pr.padre=hijo_der;
			if(temp!=null)
			{
				temp.padre=pr;
			}
			w=pr.balance;
			pr.balance=(w-1)-Math.max(hijo_der.balance,0);
			balt=Math.min((w-2),(w+hijo_der.balance-2));
			hijo_der.balance=Math.min(balt,(hijo_der.balance-1));
			
		}

	
		public void rotacionSD(Nodo pr, Nodo hijo_izq)
		{
			
			int w, balt;
			temp=hijo_izq.drcha;
			if(pr==raiz)
			{
				raiz=hijo_izq;
				hijo_izq.padre=null;
			}
			else
			{
				if(pr.dato>pr.padre.dato)
				{
					pr.padre.drcha=hijo_izq;
				}
				else
				{
					pr.padre.izqda=hijo_izq;
				}
			}
				hijo_izq.padre=pr.padre;
				hijo_izq.drcha=pr;
				pr.izqda=temp;
				pr.padre=hijo_izq;
				if(temp!=null)
				{
					temp.padre=pr;
				}
				w=pr.balance;
				pr.balance=(w+1)-Math.min(hijo_izq.balance,0);
				balt=Math.min((w+2),(w-hijo_izq.balance+2));
				hijo_izq.balance=Math.max(balt,(hijo_izq.balance+1));
			
		}
	public void eliminarelemento(int elemento)
	{

		if(raiz!=null)
		{
			temp = raiz;
			if(temp.dato ==elemento)
			{

				raiz = repla(raiz);
	
			}
			else
			{
				Nodo actual, par = raiz;
				boolean foun = false;
				if(elemento < temp.dato)
					actual = raiz.izqda;
				else
					actual = raiz.drcha;
				while(actual != null && !foun)
				{
					if(elemento==actual.dato)
					{
						foun = true;

						if(actual ==par.izqda)
						{
							par.izqda = repla(actual);
						}
						else
						{
							par.drcha = repla(actual);
						}
					}
					else
					{
						par = actual;
						if(elemento < actual.dato)
							actual = actual.izqda;
						else
							actual = actual.drcha;
					}
					
				}
				if(!foun)
					System.out.println("Elemento no encontrado");
			}

		}
	}
	public Nodo repla (Nodo nu)
	{
		Nodo result = null;
		if ((nu.izqda ==null)&& (nu.drcha == null))
			result = null;
		else if ((nu.izqda!=null)&& (nu.drcha==null))
			result = nu.izqda;
		else if ((nu.izqda ==null)&& (nu.drcha != null))
			result = nu.drcha;
		else
		{
			Nodo actual = nu.drcha;
			Nodo pa = nu;
			while (actual.izqda != null)
			{
				pa = actual;
				actual = actual.izqda;
			}
			if(nu.drcha==actual)
				actual.izqda = nu.izqda;
			else
			{
				pa.izqda= actual.drcha;
				actual.drcha = nu.drcha;
				actual.izqda = nu.izqda;
			}
			result = actual;
		}
		return result;
	}
	
	public void eliminaArbol()
	{
		if(raiz != null)
			eliminArbol(raiz);
	}
	private void eliminArbol(Nodo raiz)
	{
		if(raiz != null)
		{
			eliminArbol(raiz.izqda);
			eliminArbol(raiz.drcha);
			raiz = null;
		}
	}
	public boolean buscar(int buscado)
	{
		if (raiz != null)
			return (raiz.buscarNodo(buscado)!= null);
		else
			return false;
	}

	public void visualizar()
	{
		if (raiz != null)
		{
			
			System.out.println("Elementos del arbol: recorrido En orden\n");
			raiz.re_enorden();
			System.out.println("\nElementos del arbol: recorrido Preorden\n");
			raiz.re_preorden();
			System.out.println("\nElementos del arbol: recorrido Postorden\n");
			raiz.re_postorden();
			
		}
	}
}