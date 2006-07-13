package ucar.nc2.dataset.grid;

import junit.framework.*;
import ucar.nc2.TestAll;
import ucar.nc2.NetcdfFileCache;

/** Count geogrid objects - sanity check when anything changes. */

public class TestReadAndCountDods extends TestCase {

  public TestReadAndCountDods( String name) {
    super(name);
  }

  public void testRead() throws Exception {

    // this has a Grid that returns a structure
    doOne("dods://iridl.ldeo.columbia.edu/SOURCES/.CAYAN/dods", "", 5, 1, 3, 0);

    // this has a Grid that returns a bare array
    try {
      doOne("dods://usgodae2.usgodae.org:80/dods/GDS/coamps_cent_am/COAMPS_cent_am_0001_000000-000000ltnt_heat_flux", "", 1, 1, 3, 0);
    } catch (Exception e) {
      System.out.println(" -- barf");
    }

    // IDV netcdf files, one from each model
    String base = "thredds:resolve:http://motherlode.ucar.edu:8080/thredds/";
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?nam_211", 40, 6, 8, 4);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?gfs_211", 31, 6, 8, 4);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?gfs_37-44", 31, 4, 8, 4);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?gfs_25-26", 4, 2, 5, 1);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?ruc_211", 31, 5, 7, 3);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?ruc2_236", 48, 4, 7, 3);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?sst_21-24", 1, 1, 4, 0);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?sst_61-64", 1, 1, 4, 0);
    TestReadandCount.doOne(base,"dqc/latestModel-InvCat1.0?ocean_21-24", 5, 1, 4, 0);  // */

    // Problem with latest showing incomplete files !!
    /* Grib files, one from each model
    TestReadandCount.doOne(base,"idd/model/DGEX/CONUS_12km/latest.xml", 23, 11, 13, 8);
    TestReadandCount.doOne(base,"idd/model/DGEX/Alaska_12km/latest.xml", 23, 11, 13, 8);

    TestReadandCount.doOne(base,"idd/model/GFS/Alaska_191km/latest.xml", 22, 10, 11, 7); //
    TestReadandCount.doOne(base,"idd/model/GFS/CONUS_80km/latest.xml", 31, 11, 13, 8);
    TestReadandCount.doOne(base,"idd/model/GFS/CONUS_95km/latest.xml", 30, 10, 12, 8);
    TestReadandCount.doOne(base,"idd/model/GFS/CONUS_191km/latest.xml", 20, 8, 10, 7);
    TestReadandCount.doOne(base,"idd/model/GFS/Global_0p5deg/latest.xml", 116, 20, 18, 12);
    TestReadandCount.doOne(base,"idd/model/GFS/Global_onedeg/latest.xml", 117, 23, 20, 14);
    TestReadandCount.doOne(base,"idd/model/GFS/Global_2p5deg/latest.xml", 117, 17, 18, 14);
    TestReadandCount.doOne(base,"idd/model/GFS/Hawaii_160km/latest.xml", 15, 8, 10, 6);
    TestReadandCount.doOne(base,"idd/model/GFS/N_Hemisphere_381km/latest.xml", 14, 8, 10, 5);
    TestReadandCount.doOne(base,"idd/model/GFS/Puerto_Rico_191km/latest.xml", 15, 8, 10, 6);

    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/Alaska_11km/latest.xml", 56, 12, 14, 10);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/Alaska_22km/latest.xml", 25, 8, 10, 6);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/Alaska_45km/noaaport/latest.xml", 21, 6, 8, 4);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/Alaska_45km/conduit/latest.xml", 142, 29, 31, 28);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/Alaska_95km/latest.xml", 29, 12, 14, 9);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_12km/latest.xml", 56, 12, 14, 10);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_20km/surface/latest.xml", 54, 13, 15, 12);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_20km/selectsurface/latest.xml", 13, 3, 5, 2);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_20km/noaaport/latest.xml", 33, 8, 10, 7);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_40km/noaaport/latest.xml", 17, 6, 8, 4);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_40km/conduit/latest.xml", 168, 25, 27, 24);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/CONUS_80km/latest.xml", 41, 11, 13, 8);
    TestReadandCount.doOne(base,"idd/model/NCEP/NAM/Polar_90km/latest.xml", 131, 26, 28, 25);

    TestReadandCount.doOne(base,"idd/model/RUC/CONUS_20km/surface/latest.xml", 26, 4, 6, 3);
    TestReadandCount.doOne(base,"idd/model/RUC/CONUS_20km/pressure/latest.xml", 71, 9, 11, 8);
    TestReadandCount.doOne(base,"idd/model/RUC/CONUS_20km/hybrid/latest.xml", 50, 9, 11, 8);
    TestReadandCount.doOne(base,"idd/model/RUC/CONUS_40km/latest.xml", 48, 11, 12, 6);
    TestReadandCount.doOne(base,"idd/model/RUC/CONUS_80km/latest.xml", 31, 8, 10, 5);  // */

    //NetcdfFileCache.clearCache( true);
  }

  static void doOne(String dir, String filename, int ngrids, int ncoordSys, int ncoordAxes, int nVertCooordAxes) throws Exception {
    TestReadandCount.doOne(dir, filename, ngrids, ncoordSys, ncoordAxes, nVertCooordAxes);
  }

   public static void main( String arg[]) throws Exception {
     // new TestReadandCount("dummy").doOne("C:/data/conventions/wrf/","wrf.nc", 33, 5, 7, 7);  // missing TSLB
     new TestReadandCountGrib("dummy").testRead();  // missing TSLB
  }

}
