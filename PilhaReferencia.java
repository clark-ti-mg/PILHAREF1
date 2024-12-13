public class PilhaReferencia{
    int tamanho = 0;
    int soma = 0;
    Carta topo;

    public boolean isVazia(){
        return tamanho==0;
    }

    public int getTamanho(){
        return tamanho;
    }

    public void push(Carta carta){
        if(tamanho!=0){
            carta.proximo = topo;
        }
        topo = carta;
        soma+=carta.valor;
        ++tamanho;
    }

    public Carta pop() throws Exception{
        if(tamanho==0){
            throw new Exception("Não há elementos na pilha");
        }
        Carta carta = topo;
        topo =  topo.proximo;
        --tamanho;
        soma-=carta.valor;
        return carta;
    }
}
