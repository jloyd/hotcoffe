square = { it * it }

html.html {
    head {
        title "Hello"
    }
    body {
    	t = new Teste()		
        p "Ol� Mundo!!"
        p "Quadrado " + square(9)
        p t.nome + " " + Teste.count++
    }
}