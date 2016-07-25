using DmStlToolkit.Geometry;
using DmCad.Geometry;
using DmCommon.Utils;
using DmPrinters.Printers;

class test
{

    public void execute()
    {
        DmStlPartItem dmStlPartItem = new DmStlPartItem(null);
        dmStlPartItem.AddPreviewPrintedItem(null);
        dmStlPartItem.LoadStl("C:\\Model.stl", DmStlPartItem.eUnits.mm, DmFilesUtil.Instance);
        dmStlPartItem.MaxContactAreaPosition();

        dmStlPartItem.Slice(0.0003, new double[] { }, new double[] { });
        dmStlPartItem.AnalizeFaces();
        dmStlPartItem.Fill<DmPath>(0.0004, 0.4, 2, 45 * Math.PI / 180, true);

        string err;
        DmGenericPrinter dmPrinter = new DmRepRapMarlin();

        dmPrinter.Initialize(dmStlPartItem, DmFilesUtil.Instance, 0.003, 1800, 1000, 0.2, 190, 1);
        dmPrinter.GenerateCode("C:\\Model", out err);


    }
}