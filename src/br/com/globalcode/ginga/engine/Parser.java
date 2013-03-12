package br.com.globalcode.ginga.engine;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

public interface Parser {

	 List parse(String queryString) throws IOException, JSONException;
	 List parse(String queryString, int tamanho) throws IOException, JSONException;
<<<<<<< HEAD
}
=======
}
>>>>>>> 46371ddbee4bcdbcc31abac6882d5bf589c5bb6b
