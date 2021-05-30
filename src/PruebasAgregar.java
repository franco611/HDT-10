import static org.junit.Assert.*;

public class PruebasAgregar {

    @org.junit.Test
    public void add() {
        Grafo grafito = new Grafo(50,50);
        grafito.insertaArista(22,29,12);

        assertEquals(true,grafito.existeArista(22,29));

    }

}