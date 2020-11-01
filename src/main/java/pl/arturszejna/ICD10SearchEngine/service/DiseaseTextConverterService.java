package pl.arturszejna.ICD10SearchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturszejna.ICD10SearchEngine.entity.Disease;
import pl.arturszejna.ICD10SearchEngine.repository.DiseaseRepository;

@Service
public class DiseaseTextConverterService {

    private static String text = "(A00) - Choroba zakaźna wywołana przez Vibrio cholerae [cholera]\n" +
            "(A01) - Choroba zakaźna wywołana przez Salmonella typhi i Salmonella paratyphi [dur brzuszny i dury rzekome]\n" +
            "(A02) - Inne zakażenia wywołane przez Salmonella\n" +
            "(A03) - Choroba zakaźna wywołana przez Shigella [szigelloza]\n" +
            "(A04) - Inne bakteryjne zakażenia jelit\n" +
            "(A05) - Inne bakterie zawarte w pokarmie powodujące zatrucia niesklasyfikowane gdzie indziej\n" +
            "(A06) - Choroba zakaźna wywołana przez Entamoeba histolytica [pełzakowica] [ameboza]\n" +
            "(A07) - Inne pierwotniakowe choroby jelit\n" +
            "(A08) - Wirusowe i inne określone zakażenia jelit\n" +
            "(A09) - Biegunka i zapalenie żołądkowo-jelitowe o przypuszczalnie zakaźnej etiologii\n" +
            "(A15) - Gruźlica układu oddechowego, potwierdzona bakteriologicznie i histologicznie\n" +
            "(A16) - Gruźlica układu oddechowego, niepotwierdzona bakteriologicznie lub histologicznie\n" +
            "(A17) - Gruźlica układu nerwowego\n" +
            "(A18) - Gruźlica innych narządów\n" +
            "(A19) - Gruźlica prosówkowa\n" +
            "(A20) - Choroba zakaźna wywołana przez Yersinia pestis [dżuma]\n" +
            "(A21) - Choroba zakaźna wywołana przez Francisella tularensis [tularemia]\n" +
            "(A22) - Choroba zakaźna wywołana przez Bacillus anthracis [wąglik]\n" +
            "(A23) - Choroba zakaźna wywołana przez Brucella [bruceloza]\n" +
            "(A24) - Choroba zakaźna wywołana przez Burkholderia mallei [nosacizna] i Burkholderia pseudomallei [melioidoza]\n" +
            "(A25) - Gorączka szczurza\n" +
            "(A26) - Choroba zakaźna wywołana przez Erysipelothrix rhusiopathiae [różyca]\n" +
            "(A27) - Choroba odzwierzęca wywołana przez Leptospira interrogans [leptospiroza]\n" +
            "(A28) - Inne odzwierzęce choroby bakteryjne, niesklasyfikowane gdzie indziej\n" +
            "(A30) - Choroba zakaźna wywołana przez Mycobacterium leprae [trąd] [choroba Hansena]\n" +
            "(A31) - Zakażenie wywołane przez inne prątki\n" +
            "(A32) - Choroba zakaźna wywołana przez Listeria monocytogenes [listerioza]\n" +
            "(A33) - Choroba zakaźna wywołana przez Clostridium tetani występująca u noworodków [tężec noworodków]\n" +
            "(A34) - Choroba zakaźna wywołana przez Clostridium tetani występująca u ciężarnych [tężec położniczy]\n" +
            "(A35) - Inne postacie tężca\n" +
            "(A36) - Choroba zakaźna wywołana przez Corynebacterium diphtheriae [błonica]\n" +
            "(A37) - Choroba zakaźna wywołana przez Bordetella [krztusiec]\n" +
            "(A38) - Choroba zakaźna wywołana przez Streptococcus pyogenes [płonica]\n" +
            "(A39) - Zakażenie wywołane przez Neisseria meningitidis\n" +
            "(A40) - Posocznica wywołana przez paciorkowce\n" +
            "(A41) - Inna posocznica\n" +
            "(A42) - Choroba zakaźna wywołana przez Actinomyces israeli [promienica]\n" +
            "(A43) - Choroba zakaźna wywołana przez Nocardia [nokardioza]\n" +
            "(A44) - Choroba zakaźna wywołana przez Bartonella [bartonelloza]\n" +
            "(A46) - Róża\n" +
            "(A48) - Inne choroby bakteryjne niesklasyfikowane gdzie indziej\n" +
            "(A49) - Zakażenia bakteryjne o nieokreślonym umiejscowieniu\n" +
            "(A50) - Kiła wrodzona\n" +
            "(A51) - Kiła wczesna\n" +
            "(A52) - Kiła późna\n" +
            "(A53) - Inne postacie kiły i kiła nieokreślona\n" +
            "(A54) - Rzeżączka\n" +
            "(A55) - Ziarnica weneryczna wywołana przez Chlamydia\n" +
            "(A56) - Inne choroby przenoszone drogą płciową wywołane przez Chlamydia\n" +
            "(A57) - Wrzód weneryczny\n" +
            "(A58) - Ziarniniak pachwinowy\n" +
            "(A59) - Rzęsistkowica\n" +
            "(A60) - Opryszczkowe [herpes simplex] zakażenie okolicy anogenitalnej\n" +
            "(A63) - Inne choroby przenoszone głównie drogą płciową, niesklasyfikowane gdzie indziej\n" +
            "(A64) - Choroby przenoszone drogą płciową, nieokreślone\n" +
            "(A65) - Kiła nieweneryczna\n" +
            "(A66) - Malinica\n" +
            "(A67) - Pinta\n" +
            "(A68) - Gorączka powrotna\n" +
            "(A69) - Zakażenia wywołane przez inne krętki\n" +
            "(A70) - Zakażenie wywołane przez Chlamydia psittaci\n" +
            "(A71) - Jaglica\n" +
            "(A74) - Inne choroby wywołane przez Chlamydia\n" +
            "(A75) - Dur wysypkowy\n" +
            "(A77) - Dur plamisty [riketsjoza przenoszona przez kleszcze]\n" +
            "(A78) - Gorączka Q\n" +
            "(A79) - Inne choroby wywołane przez Rickettsia\n" +
            "(A80) - Ostre nagminne porażenie dziecięce\n" +
            "(A81) - Atypowe wirusowe zakażenia ośrodkowego układu nerwowego\n" +
            "(A82) - Wścieklizna\n" +
            "(A83) - Wirusowe zapalenie mózgu wywołane przez wirusy przenoszone przez komary\n" +
            "(A84) - Wirusowe zapalenie mózgu wywołane przez wirusy przenoszone przez kleszcze\n" +
            "(A85) - Inne wirusowe zapalenie mózgu niesklasyfikowane gdzie indziej\n" +
            "(A86) - Wirusowe zapalenie mózgu, nieokreślone\n" +
            "(A87) - Wirusowe zapalenie opon mózgowo-rdzeniowych\n" +
            "(A88) - Inne wirusowe zakażenia ośrodkowego układu nerwowego niesklasyfikowane gdzie indziej\n" +
            "(A89) - Wirusowe zakażenie ośrodkowego układu nerwowego, nieokreślone\n" +
            "(A90) - Gorączka denga [denga klasyczna]\n" +
            "(A91) - Gorączka krwotoczna denga\n" +
            "(A92) - Inne gorączki wirusowe przenoszone przez komary\n" +
            "(A93) - Inne gorączki wirusowe przenoszone przez komary, niesklasyfikowane gdzie indziej\n" +
            "(A94) - Gorączki wirusowe przenoszone przez stawonogi, nieokreślone\n" +
            "(A95) - Żółta gorączka\n" +
            "(A96) - Gorączka krwotoczna wywołana przez arenawirusy\n" +
            "(A98) - Inne wirusowe gorączki krwotoczne niesklasyfikowane gdzie indziej\n" +
            "(A99) - Nieokreślona wirusowa gorączka krwotoczna";

    private DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseTextConverterService(DiseaseRepository diseaseRepository){
        this.diseaseRepository = diseaseRepository;
    }

    public void convertTextAndAddToDatabase() {
        String[] split = text.split("\n");
        for ( String disease : split ) {


            String code = disease.substring(1, 4);
            String name = disease.substring(8);
            Disease newDisease = Disease.of(code, name);
            //diseaseRepository.save(newDisease);
        }

    }

}
