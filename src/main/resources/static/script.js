
const url = "http://localhost:8080/jogo";
function salvar(){
    const jogo = {
        nome: document.getElementById("nome").value,
        preco: parseFloat(document.getElementById("preco").value),
        categoria: document.getElementById("categoria").value,
        codigo_ativacao: document.getElementById("codigo_ativacao").value
    };

    fetch(url, {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(jogo)}).then(response => response.json()).then(() => listar());

    document.getElementById("form").reset();
}

function listar(){
    fetch(url)
    .then(res => res.json())
    .then(dados => {
        const lista = document.getElementById("lista");
        lista.innerHTML = "";

        dados.forEach(jogo => {
            const col = document.createElement("div");
            col.className = "col-md-3 mb-4";

            col.innerHTML = `
                <div class="card h-100" style="width: 18rem;">

                    <p class="fw-bold m-2">#${jogo.id}</p>

                    <img src="./controller.svg" class="card-img-top" alt="...">

                    <div class="card-body">
                        <h5 class="card-title">${jogo.nome}</h5>
                        <p class="card-text">
                            Isto é possivelmente um jogo, basta comprar e jogar.
                        </p>
                    </div>

                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            Categoria: ${jogo.categoria}
                        </li>
                        <li class="list-group-item">
                            Preço: R$ ${jogo.preco.toFixed(2)}
                        </li>
                    </ul>

                    <div class="card-body">
                        <a href="#" onclick="comprar(${jogo.id})" class="btn btn-primary">Comprar</a>
                        <a href="#" onclick="alterar(${jogo.id})" class="btn btn-warning">Alterar</a>
                        <a onclick="deletar(${jogo.id})" href="#" class="btn btn-danger">
                            <img src="./trash3.svg" alt="Deletar" width="32" height="20">
                        </a>
                    </div>

                </div>
            `;

            lista.appendChild(col);
        });
    });
}

function alterar(id){
    fetch(`${url}/${id}`)
    .then(res => res.json())
    .then(jogo => {
        document.getElementById("alterarId").value = jogo.id;
        document.getElementById("alterarNome").value = jogo.nome;
        document.getElementById("alterarCategoria").value = jogo.categoria;
        document.getElementById("alterarPreco").value = jogo.preco;
        document.getElementById("alterarCodigo").value = jogo.codigo_ativacao;

        const modal = new bootstrap.Modal(document.getElementById('alterarModal'));
        modal.show();
    });
}

function salvarAlteracao() {
    const id = document.getElementById("alterarId").value;
    const jogo = {
        nome: document.getElementById("alterarNome").value,
        categoria: document.getElementById("alterarCategoria").value,
        preco: parseFloat(document.getElementById("alterarPreco").value),
        codigo_ativacao: document.getElementById("alterarCodigo").value
    };


    fetch(`${url}/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(jogo)
    })
    .then(res => res.json())
    .then(() => {
        // atualizar lista de jogos
        listar();

        // fechar modal
        const modalEl = document.getElementById('alterarModal');
        const modal = bootstrap.Modal.getOrCreateInstance(modalEl);
        modal.hide();
    });
}

function deletar(id){
     fetch(`${url}/${id}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
        }).then(() => listar());

}

function comprar(id){
    fetch(`${url}/${id}`).then(res => res.json()).then(jogo => {
        alert(`Código Ativação: ${jogo.codigo_ativacao}`);
    })
}


listar();