package sample.TechportService;

import java.util.ArrayList;

public class ProjectsList {
    private ArrayList<Project> projects;
    private int totalCount;

    // <editor-fold defaultstate="collapsed" desc=" getters() ">

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public int getTotalCount() {
        return totalCount;
    }

    // </editor-fold>
}

class Project{
    private int id;
    private String lastUpdated;
    private String title;
    private String acronym;
    private String status;
    private String startDate;
    private String endDate;
    private String description;
    private String benefits;
    private String technologyMaturityStart;
    private String technologyMaturityCurrent;
    private String technologyMaturityEnd;
    private ArrayList<String> destinations;
    private String supportedMissionType;
    private String responsibleProgram;
    private Organization leadOrganization;
    private ArrayList<String> workLocations;
    private ArrayList<String> programDirectors;
    private ArrayList<String> projectManagers;
    private ArrayList<String> principalInvestigators;
    private String website;
    private ArrayList<LibraryItem> libraryItems;
    private ArrayList<String> closeoutDocuments;
    private ArrayList<Organization> supportingOrganizations;
    private ArrayList<Organization> coFundingPartners;
    private ArrayList<ImpactArea> primaryTas;
    private ArrayList<ImpactArea> additionalTas;

    // <editor-fold defaultstate="collapsed" desc=" getters() ">

    public int getId() {
        return id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getTechnologyMaturityStart() {
        return technologyMaturityStart;
    }

    public String getTechnologyMaturityCurrent() {
        return technologyMaturityCurrent;
    }

    public String getTechnologyMaturityEnd() {
        return technologyMaturityEnd;
    }

    public ArrayList<String> getDestinations() {
        return destinations;
    }

    public String getSupportedMissionType() {
        return supportedMissionType;
    }

    public String getResponsibleProgram() {
        return responsibleProgram;
    }

    public Organization getLeadOrganization() {
        return leadOrganization;
    }

    public ArrayList<String> getWorkLocations() {
        return workLocations;
    }

    public ArrayList<String> getProgramDirectors() {
        return programDirectors;
    }

    public ArrayList<String> getProjectManagers() {
        return projectManagers;
    }

    public ArrayList<String> getPrincipalInvestigators() {
        return principalInvestigators;
    }

    public String getWebsite() {
        return website;
    }

    public ArrayList<LibraryItem> getLibraryItems() {
        return libraryItems;
    }

    public ArrayList<String> getCloseoutDocuments() {
        return closeoutDocuments;
    }

    public ArrayList<Organization> getSupportingOrganizations() {
        return supportingOrganizations;
    }

    public ArrayList<Organization> getCoFundingPartners() {
        return coFundingPartners;
    }

    public ArrayList<ImpactArea> getPrimaryTas() {
        return primaryTas;
    }

    public ArrayList<ImpactArea> getAdditionalTas() {
        return additionalTas;
    }

    // </editor-fold>

}

class LibraryItem{
    private int id;
    private String title;
    private String type;
    private String description;
    private String externalUrl;
    private String publishedBy;
    private String publishedDate;
    private ArrayList<LibraryFile> files;

    // <editor-fold defaultstate="collapsed" desc=" getters() ">

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public ArrayList<LibraryFile> getFiles() {
        return files;
    }

    // </editor-fold>
}

class LibraryFile{
    private int id;
    private String url;
    private int size;

    // <editor-fold defaultstate="collapsed" desc=" getters() ">

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getSize() {
        return size;
    }

    // </editor-fold>
}

class Organization{
    private String name;
    private String type;
    private String acronym;
    private String city;
    private String state;

    // <editor-fold defaultstate="collapsed" desc=" getters() ">

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAcronym() {
        return acronym;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    // </editor-fold>
}

class ImpactArea{
    private int id;
    private String code;
    private String name;
    private String priority;

    // <editor-fold defaultstate="collapsed" desc=" getters() ">

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    // </editor-fold>
}

