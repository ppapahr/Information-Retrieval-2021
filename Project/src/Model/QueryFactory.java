package Model;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;



public class QueryFactory {
	
	public QueryFactory() {
		
	}
	
	public Query createQuery(String field, String inputText) throws IOException, ParseException {
		
		if (field.contentEquals("All")) {
			
			Query query1 = new TermQuery(new Term("Title", inputText));
			Query query2 = new TermQuery(new Term("Author", inputText));
			Query query3 = new TermQuery(new Term("Abstract", inputText));
			Query query4 = new TermQuery(new Term("Body", inputText));
			
			BooleanQuery.Builder builder = new BooleanQuery.Builder();
			builder.add(query1, Occur.SHOULD);
			builder.add(query2, Occur.SHOULD);
			builder.add(query3, Occur.SHOULD);
			builder.add(query4, Occur.SHOULD);
			
			BooleanQuery booleanQuery = builder.build();
			return booleanQuery;			
		}
		
		QueryParser qp = new QueryParser(field, new StandardAnalyzer());
		
		Query query = qp.parse(inputText);
		
		return query;
	}

}
