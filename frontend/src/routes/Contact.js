import Hero from "../components/Hero";
import ContactUs from "../components/ContactUs";
import contactUsImg from "../assets/contactUsImg.jpg";
function Contact() {
  return (
    <>
      <Hero
        cName="hero-mid"
        heroImg={contactUsImg}
        title="Contact Us"
        btnClass="hide"
      />
      <ContactUs />
    </>
  );
}

export default Contact;
