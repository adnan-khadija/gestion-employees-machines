document.addEventListener('DOMContentLoaded', function () {
    const translations = {
        'fr': {
            'employeFormTitle': 'Formulaire Employé',
            'employeLabelName': 'Nom:',
            'employeLabelFirstName': 'Prénom:',
            'employeLabelSalary': 'Salaire:',
            'employeButtonSave': 'Enregistrer',
            'employeListTitle': 'Retour à la liste des employés',
            'home': 'Accueil',
            'employeEdit': 'Modifier',
            'employeDelete': 'Supprimer',
            'employeAdd': 'Ajouter un employé',
            'machineChartTitle': 'Machine Acquisition Chart',
            'machineFormTitle': 'Formulaire Machine',
                'machineLabelReference': 'Référence:',
                'machineLabelBrand': 'Marque:',
                'machineLabelPurchaseDate': 'Date d\'Achat:',
                'machineLabelPrice': 'Prix:',
                'machineLabelEmployee': 'Employé:',
                'machineButtonSave': 'Enregistrer',
                'machineListTitle': 'Retour à la liste des machines',
                'homePageTitle': 'Bienvenue sur la Page d\'Accueil',
                'welcomeText': 'Explorez les fonctionnalités de gestion des employés et des machines.',
                'employeeForm': 'Formulaire Employé',
                'machineForm': 'Formulaire Machine',
                'statistique':'statistiques',
                'machineListTitle': 'Liste des Machines',
            'machineEdit': 'Modifier',
            'machineDelete': 'Supprimer',
            'machineAdd': 'Ajouter une machine',
            'employeeFilterTitle': 'Filtrer par employé',
            'employeeFilterLabel': 'Choisir un employé',
            'allEmployees': 'Tous les employés',
            'filterButton': 'Filtrer',
            'dateFilterTitle': 'Filtrer par date d\'achat',
            'startDateLabel': 'Date de début',
            'endDateLabel': 'Date de fin',
            'dateFilterButton': 'Filtrer par date',
            'priceLabel': 'Prix',
            'brandLabel': 'Marque',
            'referenceLabel': 'Référence',
            'employeeLabel': 'Employé',
        },
        'en': {
            'employeFormTitle': 'Employee Form',
            'employeLabelName': 'Name:',
            'employeLabelFirstName': 'First Name:',
            'employeLabelSalary': 'Salary:',
            'employeButtonSave': 'Save',
            'employeListTitle': 'Back to Employee List',
            'home': 'Home',
            'employeEdit': 'Edit',
            'employeDelete': 'Delete',
            'employeAdd': 'Add Employee',
            'machineChartTitle': 'Graphique d\'acquisition de machines',
            'machineFormTitle': 'Machine Form',
            'machineLabelReference': 'Reference:',
            'machineLabelBrand': 'Brand:',
            'machineLabelPurchaseDate': 'Purchase Date:',
            'machineLabelPrice': 'Price:',
            'machineLabelEmployee': 'Employee:',
            'machineButtonSave': 'Save',
            'machineListTitle': 'Back to Machine List',
            'purchaseDateLabel': 'Date d\'Achat',
            'actionsLabel': 'Actions',
            'machineListTitle': 'Machine List',
                'machineEdit': 'Edit',
                'machineDelete': 'Delete',
                'machineAdd': 'Add a machine',
                'employeeFilterTitle': 'Filter by employee',
                'employeeFilterLabel': 'Choose an employee',
                'allEmployees': 'All employees',
                'filterButton': 'Filter',
                'dateFilterTitle': 'Filter by purchase date',
                'startDateLabel': 'Start Date',
                'endDateLabel': 'End Date',
                'dateFilterButton': 'Filter by date',
                'priceLabel': 'Price',
                'brandLabel': 'Brand',
                'referenceLabel': 'Reference',
                'employeeLabel': 'Employee',
                'purchaseDateLabel': 'Purchase Date',
                'actionsLabel': 'Actions',
                
                'homePageTitle': 'Welcome to the Home Page',
                'welcomeText': 'Explore the features for managing employees and machines.',
                'employeeForm': 'Employee Form',
                'machineForm': 'Machine Form',
                'statistique':'statistics'
                

        }
    };

    function changeLanguage(lang) {
        const translatedTexts = translations[lang];

        for (const key in translatedTexts) {
            if (Object.prototype.hasOwnProperty.call(translatedTexts, key)) {
                const elements = document.querySelectorAll(`[data-translate='${key}']`);
                elements.forEach(element => {
                    element.textContent = translatedTexts[key];
                });
            }
        }

        sessionStorage.setItem('selectedLang', lang);
    }

    document.body.addEventListener('click', function (event) {
        if (event.target.classList.contains('language-selector')) {
            const selectedLang = event.target.getAttribute('data-lang');
            changeLanguage(selectedLang);
        }
    });

    const storedLang = sessionStorage.getItem('selectedLang');

    if (storedLang) {
        changeLanguage(storedLang);
    } else {
        changeLanguage('fr');
    }
});
