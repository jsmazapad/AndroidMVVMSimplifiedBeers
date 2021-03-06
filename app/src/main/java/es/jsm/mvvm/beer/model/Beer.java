package es.jsm.mvvm.beer.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import es.jsm.mvvm.beer.data.database.ListConverter;

/**
 * Entidad que representa a una cerveza
 * Está preparada para:
 * - serializarse y deserializarse a JSON (GSON) -> Tags SerializedName y Expose
 * - ser almacenada en base de datos (ROOM) -> Resto de Tags
 * - ser enviada entre activities ya que implementa la interfaz Parcelable
 */
@Entity(tableName = Beer.TABLE_NAME)
public class Beer implements Parcelable {

    /** The name of the table. */
    public static final String TABLE_NAME = "places";

    /** The name of the ID column. */
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private int internalId;

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("first_brewed")
    @Expose
    private String firstBrewed;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("abv")
    @Expose
    private double abv;
    @SerializedName("ibu")
    @Expose
    private int ibu;
    @SerializedName("target_fg")
    @Expose
    private int targetFg;
    @SerializedName("target_og")
    @Expose
    private int targetOg;
    @SerializedName("ebc")
    @Expose
    private int ebc;
    @SerializedName("srm")
    @Expose
    private int srm;
    @SerializedName("ph")
    @Expose
    private double ph;
    @SerializedName("attenuation_level")
    @Expose
    private int attenuationLevel;
    @SerializedName("food_pairing")
    @Expose
    @TypeConverters(ListConverter.class)
    private List<String> foodPairing = null;
    @SerializedName("brewers_tips")
    @Expose
    private String brewersTips;
    @SerializedName("contributed_by")
    @Expose
    private String contributedBy;
    public final static Parcelable.Creator<Beer> CREATOR = new Creator<Beer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        public Beer[] newArray(int size) {
            return (new Beer[size]);
        }

    };

    protected Beer(Parcel in) {
        this.internalId = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.tagline = ((String) in.readValue((String.class.getClassLoader())));
        this.firstBrewed = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.abv = ((double) in.readValue((double.class.getClassLoader())));
        this.ibu = ((int) in.readValue((int.class.getClassLoader())));
        this.targetFg = ((int) in.readValue((int.class.getClassLoader())));
        this.targetOg = ((int) in.readValue((int.class.getClassLoader())));
        this.ebc = ((int) in.readValue((int.class.getClassLoader())));
        this.srm = ((int) in.readValue((int.class.getClassLoader())));
        this.ph = ((double) in.readValue((double.class.getClassLoader())));
        this.attenuationLevel = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.foodPairing, (java.lang.String.class.getClassLoader()));
        this.brewersTips = ((String) in.readValue((String.class.getClassLoader())));
        this.contributedBy = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    @Ignore
    public Beer() {
    }

    /**
     * @param contributedBy
     * @param targetOg
     * @param foodPairing
     * @param ebc
     * @param description
     * @param srm
     * @param attenuationLevel
     * @param abv
     * @param imageUrl
     * @param name
     * @param ph
     * @param tagline
     * @param firstBrewed
     * @param targetFg
     * @param brewersTips
     * @param id
     * @param ibu
     */
    public Beer(int internalId, int id, String name, String tagline, String firstBrewed, String description, String imageUrl, double abv, int ibu, int targetFg, int targetOg, int ebc, int srm, double ph, int attenuationLevel, List<String> foodPairing, String brewersTips, String contributedBy) {
        super();
        this.internalId = internalId;
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
        this.ibu = ibu;
        this.targetFg = targetFg;
        this.targetOg = targetOg;
        this.ebc = ebc;
        this.srm = srm;
        this.ph = ph;
        this.attenuationLevel = attenuationLevel;
        this.foodPairing = foodPairing;
        this.brewersTips = brewersTips;
        this.contributedBy = contributedBy;
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public int getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(int targetFg) {
        this.targetFg = targetFg;
    }

    public int getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(int targetOg) {
        this.targetOg = targetOg;
    }

    public int getEbc() {
        return ebc;
    }

    public void setEbc(int ebc) {
        this.ebc = ebc;
    }

    public int getSrm() {
        return srm;
    }

    public void setSrm(int srm) {
        this.srm = srm;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public int getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(int attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public List<String> getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(List<String> foodPairing) {
        this.foodPairing = foodPairing;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(internalId);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(tagline);
        dest.writeValue(firstBrewed);
        dest.writeValue(description);
        dest.writeValue(imageUrl);
        dest.writeValue(abv);
        dest.writeValue(ibu);
        dest.writeValue(targetFg);
        dest.writeValue(targetOg);
        dest.writeValue(ebc);
        dest.writeValue(srm);
        dest.writeValue(ph);
        dest.writeValue(attenuationLevel);
        dest.writeList(foodPairing);
        dest.writeValue(brewersTips);
        dest.writeValue(contributedBy);
    }

    public int describeContents() {
        return 0;
    }

}