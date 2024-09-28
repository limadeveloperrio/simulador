package simulador.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import simulador.modelos.Produto;
import simulador.repositories.RepositoryProduto;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		log("Iniciando a servlet");
	}

	public void destroy() {
		super.destroy();
		log("Destruindo a servlet");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String diretorioAtual = System.getProperty("user.dir");
		File arquivo = new File(diretorioAtual, "/legado/produtos.csv");
		RepositoryProduto repositoryProduto = new RepositoryProduto(arquivo.getAbsolutePath());
		List<Produto> produtos = repositoryProduto.getProdutos();
		Gson gson = new Gson();
		String json = gson.toJson(produtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}
}
