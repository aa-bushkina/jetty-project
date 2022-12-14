/*
 * This file is generated by jOOQ.
 */
package generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Products implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Integer id;
  private final String name;
  private final Integer organizationId;
  private final Integer amount;

  public Products(Products value) {
    this.id = value.id;
    this.name = value.name;
    this.organizationId = value.organizationId;
    this.amount = value.amount;
  }

  public Products(
    Integer id,
    String name,
    Integer organizationId,
    Integer amount
  ) {
    this.id = id;
    this.name = name;
    this.organizationId = organizationId;
    this.amount = amount;
  }

  /**
   * Getter for <code>public.products.id</code>.
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Getter for <code>public.products.name</code>.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter for <code>public.products.organization_id</code>.
   */
  public Integer getOrganizationId() {
    return this.organizationId;
  }

  /**
   * Getter for <code>public.products.amount</code>.
   */
  public Integer getAmount() {
    return this.amount;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Products other = (Products) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals(other.name))
      return false;
    if (this.organizationId == null) {
      if (other.organizationId != null)
        return false;
    } else if (!this.organizationId.equals(other.organizationId))
      return false;
    if (this.amount == null) {
      if (other.amount != null)
        return false;
    } else if (!this.amount.equals(other.amount))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
    result = prime * result + ((this.organizationId == null) ? 0 : this.organizationId.hashCode());
    result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Products (");

    sb.append(id);
    sb.append(", ").append(name);
    sb.append(", ").append(organizationId);
    sb.append(", ").append(amount);

    sb.append(")");
    return sb.toString();
  }
}
