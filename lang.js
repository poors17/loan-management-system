const translations = {
  en: {
    loginTitle: "Loan Management System Login",
    usernameLabel: "Username",
    passwordLabel: "Password",
    loginButton: "Login",
    dashboardTitle: "Dashboard",
    logoutButton: "Logout",
    userManagement: "User Management",
    loanApplications: "Loan Applications",
    approveButton: "Approve",
    rejectButton: "Reject",
    languageSwitch: "தமிழ்",
    // Add other keys as needed
  },
  ta: {
    loginTitle: "கடன் மேலாண்மை அமைப்பில் உள்நுழையவும்",
    usernameLabel: "பயனர் பெயர்",
    passwordLabel: "கடவுச்சொல்",
    loginButton: "உள்நுழையவும்",
    dashboardTitle: "டாஷ்போர்ட்",
    logoutButton: "வெளியேறு",
    userManagement: "பயனர் மேலாண்மை",
    loanApplications: "கடன் விண்ணப்பங்கள்",
    approveButton: "ஒப்புதல்",
    rejectButton: "நிராகரிக்கவும்",
    languageSwitch: "English",
    // Add other keys as needed
  }
};

let currentLang = localStorage.getItem("lang") || "en";

function setLanguage(lang) {
  currentLang = lang;
  localStorage.setItem("lang", lang);
  document.querySelectorAll("[data-i18n]").forEach(el => {
    const key = el.getAttribute("data-i18n");
    if (translations[lang] && translations[lang][key]) {
      el.innerText = translations[lang][key];
    }
  });

  const langBtn = document.getElementById("langSwitchBtn");
  if (langBtn) {
    langBtn.innerText = translations[lang].languageSwitch;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  setLanguage(currentLang);

  const langBtn = document.getElementById("langSwitchBtn");
  if (langBtn) {
    langBtn.addEventListener("click", () => {
      const newLang = currentLang === "en" ? "ta" : "en";
      setLanguage(newLang);
    });
  }
});
