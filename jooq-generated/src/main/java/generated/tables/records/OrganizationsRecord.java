/*
 * This file is generated by jOOQ.
 */
package generated.tables.records;


import generated.tables.Organizations;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganizationsRecord extends UpdatableRecordImpl<OrganizationsRecord> implements Record3<Integer, String, String> {

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

    /**
     * Setter for <code>public.organizations.inn</code>.
     */
    public OrganizationsRecord setInn(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.organizations.inn</code>.
     */
    public String getInn() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
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
    public Field<String> field3() {
        return Organizations.ORGANIZATIONS.INN;
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
    public String component3() {
        return getInn();
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
    public String value3() {
        return getInn();
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
    public OrganizationsRecord value3(String value) {
        setInn(value);
        return this;
    }

    @Override
    public OrganizationsRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
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
    public OrganizationsRecord(Integer id, String name, String inn) {
        super(Organizations.ORGANIZATIONS);

        setId(id);
        setName(name);
        setInn(inn);
    }

    /**
     * Create a detached, initialised OrganizationsRecord
     */
    public OrganizationsRecord(generated.tables.pojos.Organizations value) {
        super(Organizations.ORGANIZATIONS);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setInn(value.getInn());
        }
    }
}
