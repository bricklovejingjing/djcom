
package org.tpri.djcom.entity.zbsc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.tpri.djcom.core.ObjectBase;
import org.tpri.djcom.core.ObjectType;
/**
 * @description 文章所属栏目bean
 * @author 易文俊
 * @since 2015-04-30
 */
@Entity
@Table(name="ZBSC_ARTICLECATEGORY")
public class ArticleCategory extends ObjectBase {
	
	protected int objectType = ObjectType.ZBSC_ARTICLECATEGORY;
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected String articleId;
	protected String categoryId;
	protected String cCPartyId;
	protected Category category;

	@ManyToOne(cascade=CascadeType.MERGE,targetEntity=Category.class,fetch=FetchType.EAGER)
	@JoinColumn(name="CATEGORYID")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="ARTICLEID")
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	/*@Column(name="CATEGORYID")
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}*/
	@Column(name="CCPARTYID")
	public String getcCPartyId() {
		return cCPartyId;
	}
	public void setcCPartyId(String cCPartyId) {
		this.cCPartyId = cCPartyId;
	}
}
