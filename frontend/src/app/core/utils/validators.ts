export function getMaxBirthDate(minAge: number = 18): string {
    const today = new Date();
    const year = today.getFullYear() - minAge;
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

export function filterOnlyNumbers(value: string): string {
  return value.replace(/[^0-9]/g, '');
}