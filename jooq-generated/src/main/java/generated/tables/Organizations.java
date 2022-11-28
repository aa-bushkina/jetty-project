/*
 * This file is generated by jOOQ.
 */
package generated.tables;


import generated.Keys;
import generated.Public;
import generated.tables.records.OrganizationsRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Organizations extends TableImpl<OrganizationsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.organizations</code>
     */
    public static final Organizations ORGANIZATIONS = new Organizations();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrganizationsRecord> getRecordType() {
        return OrganizationsRecord.class;
    }

    /**
     * The column <code>public.organizations.id</code>.
     */
    public final TableField<OrganizationsRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.organizations.name</code>.
     */
    public final TableField<OrganizationsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.organizations.inn</code>.
     */
    public final TableField<OrganizationsRecord, String> INN = createField(DSL.name("inn"), SQLDataType.VARCHAR(10).nullable(false), this, "");

    private Organizations(Name alias, Table<OrganizationsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Organizations(Name alias, Table<OrganizationsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.organizations</code> table reference
     */
    public Organizations(String alias) {
        this(DSL.name(alias), ORGANIZATIONS);
    }

    /**
     * Create an aliased <code>public.organizations</code> table reference
     */
    public Organizations(Name alias) {
        this(alias, ORGANIZATIONS);
    }

    /**
     * Create a <code>public.organizations</code> table reference
     */
    public Organizations() {
        this(DSL.name("organizations"), null);
    }

    public <O extends Record> Organizations(Table<O> child, ForeignKey<O, OrganizationsRecord> key) {
        super(child, key, ORGANIZATIONS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<OrganizationsRecord, Integer> getIdentity() {
        return (Identity<OrganizationsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<OrganizationsRecord> getPrimaryKey() {
        return Keys.ORGANIZATION_PK;
    }

    @Override
    public List<UniqueKey<OrganizationsRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.ORGANIZATIONS_INN_KEY);
    }

    @Override
    public List<Check<OrganizationsRecord>> getChecks() {
        return Arrays.asList(
            Internal.createCheck(this, DSL.name("check_inn"), "(((inn)::text ~ '\\d{10}'::text))", true)
        );
    }

    @Override
    public Organizations as(String alias) {
        return new Organizations(DSL.name(alias), this);
    }

    @Override
    public Organizations as(Name alias) {
        return new Organizations(alias, this);
    }

    @Override
    public Organizations as(Table<?> alias) {
        return new Organizations(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Organizations rename(String name) {
        return new Organizations(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Organizations rename(Name name) {
        return new Organizations(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Organizations rename(Table<?> name) {
        return new Organizations(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Integer, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Integer, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
