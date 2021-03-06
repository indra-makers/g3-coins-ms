package com.co.indra.coinmarketcap.coins.repositories;

import com.co.indra.coinmarketcap.coins.model.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class CategoryRowMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		category.setIdCategory(rs.getLong("id_category"));
		category.setNameCategory(rs.getString("name_category"));
		category.setDescription(rs.getString("description"));
		return category;
	}

}

@Repository
public class CategoryRepository {

	@Autowired
	private JdbcTemplate template;

	public void createCategory(Category category) {
		template.update("INSERT INTO tbl_categories(name_category,description) values(?,?)", category.getNameCategory(),
				category.getDescription());
	}
	
	public List<Category> findCategoryById(int idCategory) {
		return template.query("SELECT id_category,name_category,description from tbl_categories where id_category = ?",new CategoryRowMapper(),idCategory);
	}

}