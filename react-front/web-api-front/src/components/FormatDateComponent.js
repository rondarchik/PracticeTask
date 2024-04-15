export default function formatDate(dateString) {
    const options = {day: '2-digit', month: '2-digit', year: 'numeric'};
    return new Date(dateString).toLocaleDateString(undefined, options);
}
