/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.Organizations;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class OrganizationsRecord extends UpdatableRecordImpl<OrganizationsRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.organizations.id</code>.
     */
    public OrganizationsRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.organizations.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.organizations.name</code>.
     */
    public OrganizationsRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.organizations.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Organizations.ORGANIZATIONS.ID;
    }

    @Override
    public Field<String> field2() {
        return Organizations.ORGANIZATIONS.NAME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public OrganizationsRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public OrganizationsRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public OrganizationsRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrganizationsRecord
     */
    public OrganizationsRecord() {
        super(Organizations.ORGANIZATIONS);
    }

    /**
     * Create a detached, initialised OrganizationsRecord
     */
    public OrganizationsRecord(Integer id, String name) {
        super(Organizations.ORGANIZATIONS);

        setId(id);
        setName(name);
    }

    /**
     * Create a detached, initialised OrganizationsRecord
     */
    public OrganizationsRecord(generated.tables.pojos.Organizations value) {
        super(Organizations.ORGANIZATIONS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
        }
    }
}
