import "../styles/ContactUsStyles.css";

function ContactUs() {
  return (
    <div className="contact">
      <h1>Send a message to us!</h1>
      <form>
        <input placeholder="Name" />
        <input placeholder="Email" />
        <input placeholder="Message" />
        <textarea placeholder="Write your message here" rows="4" />
        <button>Send Message</button>
      </form>
    </div>
  );
}

export default ContactUs;
